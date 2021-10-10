package il.ac.tau.cs.sw1.trivia;

import java.io.IOException;
import java.util.*;

import com.sun.tools.javac.util.ArrayUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import sun.security.util.ArrayUtil;

public class TriviaGUI {

	private static final int MAX_ERRORS = 3;
	private Shell shell;
	private Label scoreLabel;
	private Composite questionPanel;
	private Label startupMessageLabel;
	private Font boldFont;
	private String lastAnswer = "";
	
	// Currently visible UI elements.
	Label instructionLabel;
	Label questionLabel;
	private List<Button> answerButtons = new LinkedList<>();
	private Button passButton;
	private Button fiftyFiftyButton;

	// Keeping Score
	private int currentScore = 0;
	private int wrongAnswers = 0;
	private int answeredQuestions = 0;

	// Savers Status
	private boolean isFirstPass = true;
	private boolean isFirst50 = true;

	// Question Manager
	private QuestionsManager QM;

	public void open() {
		createShell();
		runApplication();
	}

	/**
	 * Creates the widgets of the application main window
	 */
	private void createShell() {
		Display display = Display.getDefault();
		shell = new Shell(display);
		shell.setText("Trivia");

		// window style
		Rectangle monitor_bounds = shell.getMonitor().getBounds();
		shell.setSize(new Point(monitor_bounds.width / 3,
				monitor_bounds.height / 4));
		shell.setLayout(new GridLayout());

		FontData fontData = new FontData();
		fontData.setStyle(SWT.BOLD);
		boldFont = new Font(shell.getDisplay(), fontData);

		// create window panels
		createFileLoadingPanel();
		createScorePanel();
		createQuestionPanel();
	}

	/**
	 * Creates the widgets of the form for trivia file selection
	 */
	private void createFileLoadingPanel() {
		final Composite fileSelection = new Composite(shell, SWT.NULL);
		fileSelection.setLayoutData(GUIUtils.createFillGridData(1));
		fileSelection.setLayout(new GridLayout(4, false));

		final Label label = new Label(fileSelection, SWT.NONE);
		label.setText("Enter trivia file path: ");

		// text field to enter the file path
		final Text filePathField = new Text(fileSelection, SWT.SINGLE
				| SWT.BORDER);
		filePathField.setLayoutData(GUIUtils.createFillGridData(1));

		// "Browse" button
		final Button browseButton = new Button(fileSelection, SWT.PUSH);
		browseButton.setText("Browse");
		browseButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String filePath = GUIUtils.getFilePathFromFileDialog(shell);
				if (!filePath.isEmpty()) {
					filePathField.setText(filePath);
				}
			}
		});

		// "Play!" button
		final Button playButton = new Button(fileSelection, SWT.PUSH);
		playButton.setText("Play!");
		playButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					QM = new QuestionsManager(filePathField.getText());
				} catch (IOException ioException) {
					GUIUtils.showErrorDialog(shell,
							"Trivia file format error: Trivia file row must contain a question and four answers, separated by tabs");
				}
				newGame();
			}
		});
	}

	private void newGame() {
		wrongAnswers = 0;
		currentScore = 0;
		answeredQuestions = 0;
		isFirst50 = true;
		isFirstPass = true;
		setNextQuestion();
	}

	private void updateScore() {
		scoreLabel.setText(Integer.toString(currentScore));
	}

	private boolean isGameOver() {
		return (wrongAnswers == MAX_ERRORS) || (QM.isEmpty());
	}

	private void setPassStatus() {
		if (isFirstPass || currentScore > 0) {
			passButton.setEnabled(true);
			return;
		}
		passButton.setEnabled(false);
	}

	private void set50Status() {
		if (isFirst50 || currentScore > 0) {
			fiftyFiftyButton.setEnabled(true);
			return;
		}
		fiftyFiftyButton.setEnabled(false);

	}

	private void setNextQuestion() {

		if (isGameOver()) {
			GUIUtils.showInfoDialog(shell, "Game Over!",
					"Your final score is " + currentScore + " after " + answeredQuestions + " questions.");
			return;
		}

		QuestionsManager.Question nQ = QM.nextQuestion();
		updateQuestionPanel(nQ.getQuestionTitle(), nQ.getAnswersList());
	}

	private void setSaversStatus() {
		setPassStatus();
		set50Status();
	}

	private void disableRandomWrongAnswers() {
		ArrayList<Integer> answersIndexes = new ArrayList<>();
		for (int i = 0; i < 4; i++) {
			answersIndexes.add(i);
		}
		String correctAnswer = QM.getCurrentQuestion().getCorrectAnswer();
		int correctAnswerIndex = QM.getCurrentQuestion().getAnswersList().indexOf(correctAnswer);
		answersIndexes.remove(correctAnswerIndex);
		Collections.shuffle(answersIndexes);
		answersIndexes.stream().limit(2).forEach(x -> answerButtons.get(x).setEnabled(false));
	}

	/**
	 * Creates the panel that displays the current score
	 */
	private void createScorePanel() {
		Composite scorePanel = new Composite(shell, SWT.BORDER);
		scorePanel.setLayoutData(GUIUtils.createFillGridData(1));
		scorePanel.setLayout(new GridLayout(2, false));

		final Label label = new Label(scorePanel, SWT.NONE);
		label.setText("Total score:");

		// The label which displays the score; initially empty
		scoreLabel = new Label(scorePanel, SWT.NONE);
		scoreLabel.setLayoutData(GUIUtils.createFillGridData(1));
	}

	/**
	 * Creates the panel that displays the questions, as soon as the game
	 * starts. See the updateQuestionPanel for creating the question and answer
	 * buttons
	 */
	private void createQuestionPanel() {
		questionPanel = new Composite(shell, SWT.BORDER);
		questionPanel.setLayoutData(new GridData(GridData.FILL, GridData.FILL,
				true, true));
		questionPanel.setLayout(new GridLayout(2, true));

		// Initially, only displays a message
		startupMessageLabel = new Label(questionPanel, SWT.NONE);
		startupMessageLabel.setText("No question to display, yet.");
		startupMessageLabel.setLayoutData(GUIUtils.createFillGridData(2));
	}

	/**
	 * Serves to display the question and answer buttons
	 */
	private void updateQuestionPanel(String question, List<String> answers) {
		updateScore();
		// Save current list of answers.
		List<String> currentAnswers = answers;
		
		// clear the question panel
		Control[] children = questionPanel.getChildren();
		for (Control control : children) {
			control.dispose();
		}

		// create the instruction label
		instructionLabel = new Label(questionPanel, SWT.CENTER | SWT.WRAP);
		instructionLabel.setText(lastAnswer + " Answer the following question:");
		instructionLabel.setLayoutData(GUIUtils.createFillGridData(2));

		// create the question label
		questionLabel = new Label(questionPanel, SWT.CENTER | SWT.WRAP);
		questionLabel.setText(question);
		questionLabel.setFont(boldFont);
		questionLabel.setLayoutData(GUIUtils.createFillGridData(2));

		// create the answer buttons
		answerButtons.clear();
		for (int i = 0; i < 4; i++) {
			Button answerButton = new Button(questionPanel, SWT.PUSH | SWT.WRAP);
			answerButton.setText(answers.get(i));
			GridData answerLayoutData = GUIUtils.createFillGridData(1);
			answerLayoutData.verticalAlignment = SWT.FILL;
			answerButton.setLayoutData(answerLayoutData);
			answerButton.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					if (!answerButton.getText().equals(QM.getCurrentQuestion().getCorrectAnswer())) {
						currentScore -= 2;
						wrongAnswers++;
						lastAnswer = "Wrong!";
					}
					else {
						currentScore += 3;
						wrongAnswers = 0;
						lastAnswer = "Correct!";
					}
					answeredQuestions++;
//					updateScore();
					setNextQuestion();
				}
			});
			answerButtons.add(answerButton);
		}



		// create the "Pass" button to skip a question
		passButton = new Button(questionPanel, SWT.PUSH);
		passButton.setText("Pass");
		GridData data = new GridData(GridData.END, GridData.CENTER, true,
				false);
		data.horizontalSpan = 1;
		passButton.setLayoutData(data);
		passButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (!isFirstPass) {
					currentScore -= 1;
				}
				isFirstPass = false;
				passButton.setEnabled(false);
				setNextQuestion();
			}
		});
		
		// create the "50-50" button to show fewer answer options
		fiftyFiftyButton = new Button(questionPanel, SWT.PUSH);
		fiftyFiftyButton.setText("50-50");
		data = new GridData(GridData.BEGINNING, GridData.CENTER, true,
				false);
		data.horizontalSpan = 1;
		fiftyFiftyButton.setLayoutData(data);

		fiftyFiftyButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (!isFirst50) {
					currentScore -= 1;
				}
				isFirst50 = false;
				disableRandomWrongAnswers();
				fiftyFiftyButton.setEnabled(false);
			}
		});

		// two operations to make the new widgets display properly
		questionPanel.pack();
		questionPanel.getParent().layout();
		setSaversStatus();
	}

	/**
	 * Opens the main window and executes the event loop of the application
	 */
	private void runApplication() {
		shell.open();
		Display display = shell.getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
		boldFont.dispose();
	}
}
