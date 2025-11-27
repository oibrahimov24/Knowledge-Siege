package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;


/**
 * Class for the main game panel
 * Implements KeyListener and ActionListener
 */
public class GamePanel extends JPanel implements KeyListener, ActionListener {
	
	/**
	 * the game
	 */ 
    private KnowledgeSiege game;
    
    /**
     * The player
     */
    private Player player;
    
    /**
     * List of all keepers
     */
    private List<KnowledgeKeeper> keepers;
    
    /**
     * List of all ShotBoxes
     */
    private List<ShotBox> shotBoxes;
    
    /**
     * The current level
     */
    private int level;
    
    /**
     * The current score of player
     */
    private int score;
    
    /**
     * The current health of player
     */
    private int health;
    
    /**
     * The in-game timer
     */
    private Timer gameTimer;
    
    /**
     * Random factor
     */
    private SecureRandom random;
    
    /**
     * The last question the player was shot by
     */
    private String lastQuestion;
    
    /**
     * The last info the player was shot by
     */
    private String lastInfo;
    
    /**
     * SLs' questions
     */
    private List<String> questionsSL;
    
    /**
     * TAs' question
     */
    private List<String> questionsTA;
    
    /**
     * PROFs' questions
     */
    private List<String> questionsProf;
    
    /**
     * SLs' infos
     */
    private List<String> infoSL;
    
    /**
     * TAs' infos
     */
    private List<String> infoTA;
    
    /**
     * PROFs' infos
     */
    private List<String> infoProf;
    
    /**
     * The username of the player
     */
    private String username;
    
    /**
     * The logger
     */
    private Logger logger;
    
    /**
     * Decides if the score is enough to change level
     */
    private boolean shouldChangeLevel;
    
    /**
     * The path for the questions file
     */
    private static final String QUESTIONS_FILE = "questions.txt";
    
    /**
     * The path for the infos file
     */
    private static final String INFO_FILE = "info.txt";
    
    /**
     * The game window width
     */
    private static final int GAME_WIDTH = 480;
    
    /**
     * The list of SLs
     */
    private static final List<SectionLeader> SECTION_LEADERS = List.of(
        new SectionLeader(0, 50, "Efe Değişmiş", "efe.png", new SecureRandom(), ""),
        new SectionLeader(0, 50, "Ekin Gün", "ekin.png", new SecureRandom(), ""),
        new SectionLeader(0, 50, "Ahmet Şükrü Kılıç", "ahmet.png", new SecureRandom(), ""),
        new SectionLeader(0, 50, "Nazrin Mustafazadeh", "nazrin.png", new SecureRandom(), ""),
        new SectionLeader(0, 50, "Ozan Özak", "ozan.png", new SecureRandom(), ""),
        new SectionLeader(0, 50, "Abdullah Daoud", "Abdullah.png", new SecureRandom(), ""),
        new SectionLeader(0, 50, "Ertuğrul Recep Kocaman", "ertugrul.png", new SecureRandom(), ""),
        new SectionLeader(0, 50, "Burak Gerçekaslan", "burak.png", new SecureRandom(), "")
    );

    /**
     * The list of TAs
     */
    private static final List<TeachingAssistant> TEACHING_ASSISTANTS = List.of(
        new TeachingAssistant(0, 50, "Vahideh Hayyolalam", "valideh.png", new SecureRandom(), ""),
        new TeachingAssistant(0, 50, "Abdulrezzak Zekiye", "zekiye.png", new SecureRandom(), ""),
        new TeachingAssistant(0, 50, "Hamza Abuzahra", "hamza.png", new SecureRandom(), ""),
        new TeachingAssistant(0, 50, "Fatma Nur Yaşar", "fatma.png", new SecureRandom(), ""),
        new TeachingAssistant(0, 50, "Aylanur Ertürk", "aylanur.png", new SecureRandom(), "")
    );
    
    /**
     * The list of PROFs
     */
    private static final List<Professor> PROFESSORS = List.of(
        new Professor(0, 50, "Öznur Özkasap", "oznur.png", new SecureRandom(), ""),
        new Professor(0, 50, "Attila Gürsoy", "atilla.png", new SecureRandom(), "")
    );
    
    /**
     * List for SLs used in previous levels
     */
    private List<SectionLeader> usedSLs;
    
    /**
     * List for TAs used in previous levels
     */
    private List<TeachingAssistant> usedTAs;
    
    /**
     * ScoreBoard
     */
    private ScoreBoard scoreBoard;
    
    /**
     * The restart button
     */
    private JButton restartButton;

    /**
     * Constructor for GamePanel
     * @param game
     * @param username
     */
    public GamePanel(KnowledgeSiege game,String username){
        this.game=game;
        this.username=username;
        this.logger=new Logger(username);
        this.scoreBoard=new ScoreBoard();
        setFocusable(true);
        addKeyListener(this);

        random=new SecureRandom();
        player=new Player(random.nextInt(GAME_WIDTH/2),500,"player.png");
        keepers=new ArrayList<>();
        shotBoxes=new ArrayList<>();
        lastQuestion="None";
        lastInfo="None";
        level=1;
        score=0;
        health=100;
        shouldChangeLevel=false;
        usedSLs=new ArrayList<>();
        usedTAs=new ArrayList<>();
        
        restartButton = new JButton("Restart");
        restartButton.setBounds(0, 0, 200, 40);
        add(restartButton);
        
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restartGame();
            }
        });

        loadContent();
        initLevel();

        gameTimer=new Timer(16,this);//60fps
        gameTimer.start();
        logger.log("New GamePanel created for user: "+username);
        logger.log("Game started");
    }

    /**
     * Loads all the needed questions and info
     */
    private void loadContent(){
        questionsSL=new ArrayList<>();
        questionsTA=new ArrayList<>();
        questionsProf=new ArrayList<>();
        infoSL=new ArrayList<>();
        infoTA=new ArrayList<>();
        infoProf=new ArrayList<>();
        try{
            loadFile(QUESTIONS_FILE,questionsSL,questionsTA,questionsProf);
            loadFile(INFO_FILE,infoSL,infoTA,infoProf);
            if(questionsSL.isEmpty()||questionsTA.isEmpty()||questionsProf.isEmpty()||
                infoSL.isEmpty()||infoTA.isEmpty()||infoProf.isEmpty()){
                throw new GameException("Content files are empty or missing required entries");
            }
        }catch(GameException e){
            JOptionPane.showMessageDialog(this,e.getMessage(),"Content Error",JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    /**
     * Writes the contents into lists from files
     * @param fileName
     * @param slList
     * @param taList
     * @param profList
     * @throws GameException
     */
    private void loadFile(String fileName,List<String> slList,List<String> taList,List<String> profList) throws GameException{
        try(Scanner scanner=new Scanner(new File(fileName))){
            while(scanner.hasNextLine()){
                String line=scanner.nextLine();
                String[] parts=line.split("\\|",-1);
                if(parts.length!=2||parts[1].trim().isEmpty())continue;
                switch(parts[0].trim()){
                    case "SL":
                        slList.add(parts[1].trim());
                        break;
                    case "TA":
                        taList.add(parts[1].trim());
                        break;
                    case "PROF":
                        profList.add(parts[1].trim());
                        break;
                }
            }
        }catch(FileNotFoundException e){
            throw new GameException("Content file not found: "+fileName);
        }
    }

    /**
     * Starts each level and chooses the KnowledgeKeepers to spawn and spawns them
     */
    private void initLevel(){
        logger.log("Proceeded to level "+level);
        keepers.clear();
        shotBoxes.clear();
        lastQuestion="None";
        lastInfo="None";
        shouldChangeLevel=false;
        score=0;
        health=100;

        List<KnowledgeKeeper> selected = new ArrayList<>();
        if (level == 1) {
            List<SectionLeader> shuffledSLs = new ArrayList<>(SECTION_LEADERS);
            Collections.shuffle(shuffledSLs, random);
            selected.addAll(shuffledSLs.subList(0, Math.min(4, shuffledSLs.size())));
            usedSLs = new ArrayList<>(shuffledSLs.subList(0, Math.min(4, shuffledSLs.size())));
        } else if (level == 2) {
            List<SectionLeader> remainingSLs = new ArrayList<>();
            for (SectionLeader sl : SECTION_LEADERS) {
                if (!usedSLs.contains(sl)) {
                    remainingSLs.add(sl);
                }
            }
            selected.addAll(remainingSLs);
            List<TeachingAssistant> shuffledTAs = new ArrayList<>(TEACHING_ASSISTANTS);
            Collections.shuffle(shuffledTAs, random);
            selected.addAll(shuffledTAs.subList(0, Math.min(2, shuffledTAs.size())));
            usedTAs = new ArrayList<>(shuffledTAs.subList(0, Math.min(2, shuffledTAs.size())));
        } else if (level == 3) {
            List<TeachingAssistant> remainingTAs = new ArrayList<>();
            for (TeachingAssistant ta : TEACHING_ASSISTANTS) {
                if (!usedTAs.contains(ta)) {
                    remainingTAs.add(ta);
                }
            }
            selected.addAll(remainingTAs);
            selected.addAll(PROFESSORS);
        }

        ///Распределяет выбранных противников по-ровну по экрану.
        int totalWidth=430;
        int spacing=selected.size()>1 ? totalWidth/(selected.size()-1) : totalWidth/2; //сколько пикселей между 2мя противниками? делит общую зону на столько пропуском сколько есть
        for(int i=0;i<selected.size();i++){
            KnowledgeKeeper keeper=selected.get(i);
            keeper.x=Math.min(i*spacing,totalWidth);
            keeper.y=50;
            keepers.add(keeper);
        }
    }

    /**
     * Draws each object(KnowledgeKeepers...) and other things on the right
     */
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        g.setColor(Color.WHITE);
        g.fillRect(0,0,GAME_WIDTH,600);

        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(GAME_WIDTH,0,320,600);

        player.draw(g);
        for(KnowledgeKeeper keeper:keepers){
            keeper.draw(g);
        }
        for(ShotBox shot:shotBoxes){
            shot.draw(g);
        }

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial",Font.BOLD,16));
        int y=50;

        g.drawString("Health",490,y);
        y+=30;
        g.setColor(Color.RED);
        g.fillRect(490,y,health*2,25);
        g.setColor(Color.BLACK);
        g.drawRect(490,y,200,25);
        y+=50;

        g.setFont(new Font("Arial",Font.BOLD,16));
        g.drawString("Score",490,y);
        y+=30;
        g.setFont(new Font("Arial",Font.PLAIN,16));
        g.drawString(String.valueOf(score),490,y);
        y+=50;

        g.setFont(new Font("Arial",Font.BOLD,16));
        g.drawString("Questions",490,y);
        y+=30;
        g.setFont(new Font("Arial",Font.PLAIN,14));
        String questionDisplay=lastQuestion.length()>40?lastQuestion.substring(0,40)+"...":lastQuestion;
        g.drawString(questionDisplay,490,y);
        y+=50;

        g.setFont(new Font("Arial",Font.BOLD,16));
        g.drawString("Info",490,y);
        y+=30;
        g.setFont(new Font("Arial",Font.PLAIN,14));
        String infoDisplay=lastInfo.length()>40?lastInfo.substring(0,40)+"...":lastInfo;
        g.drawString(infoDisplay,490,y);
        
     
    }

    /**
     * Called everytime an action happens
     */
    @Override
    public void actionPerformed(ActionEvent e){
        updateGame();
        repaint();
    }

    /**
     * Updates the game each moment
     */
    private void updateGame() {
        shouldChangeLevel=false;

        player.update();

        for (KnowledgeKeeper keeper:keepers) {
            keeper.update(player.getX(), keepers);
            if (random.nextDouble() < keeper.getShotProbability()) {
                String content;
                boolean isQuestion;
                if (random.nextDouble()<keeper.getQuestionChance()) {
                    isQuestion=true;
                    content=getQuestion(keeper.getType());
                } else {
                    isQuestion=false;
                    content=getInfo(keeper.getType());
                }
                
                int shotX=Math.min(keeper.getX()+10, GAME_WIDTH-30);
                shotBoxes.add(new ShotBox(shotX, keeper.getY()+50, isQuestion, content, keeper.getType()));
            }
        }

        List<ShotBox> toRemove=new ArrayList<>();
        for (ShotBox shot:shotBoxes) {
            if (shouldChangeLevel) {
                break;
            }
            shot.update();
            if (shot.getY()>600) {
                toRemove.add(shot);
            } 
            else if (player.collidesWith(shot)) {
                toRemove.add(shot);
                if (shot.isQuestion()) {
                    int damage=shot.getDamage();
                    health-=damage;
                    lastQuestion=shot.getContent();
                    logger.log("Got question: "+shot.getContent()+", Damage: "+damage);
                    if (health<=0) {
                        gameOver();
                        return;
                    }
                } 
                else {
                    int points=shot.getPoints();
                    score+=points;
                    lastInfo=shot.getContent();
                    logger.log("Got info: "+shot.getContent()+", Points: "+points);
                    if (score>=(level == 1 ? 50 : level == 2 ? 100 : 150)) {
                        shouldChangeLevel=true;
                    }
                }
            }
        }
        shotBoxes.removeAll(toRemove);

        if (shouldChangeLevel) {
            if (level==3) {
                victory();
            } else {
                level++;
                initLevel();
            }
        }
    }
    
    private void restartGame() {
    	level=1;
    	score=0;
    	health=100;
    	lastQuestion="None";
    	lastInfo="None";
    	usedSLs.clear();
        usedTAs.clear();
        player=new Player(random.nextInt(GAME_WIDTH/2),500,"player.png");
        initLevel();
        requestFocusInWindow();
    }

    /**
     * Gets questions for each type of KnowledgeKeeper
     * @param type
     * @return
     */
    private String getQuestion(String type) {
        List<String> source;
        switch (type) {
            case "SL":
                source=questionsSL;
                break;
            case "TA":
                source=questionsTA;
                break;
            case "PROF":
                source=questionsProf;
                break;
            default:
                source=questionsSL;
                break;
        }
        return source.get(random.nextInt(source.size()));
    }
    /**
     * Gets info for each type of KnowledgeKeeper
     * @param type
     * @return
     */
    private String getInfo(String type) {
        List<String> source;
        
        switch (type) {
            case "SL":
                source=infoSL;
                break;
            case "TA":
                source=infoTA;
                break;
            case "PROF":
                source=infoProf;
                break;
            default:
                source=infoSL;
                break;
        }
        return source.get(random.nextInt(source.size()));
    }

    /**
     * Is called when Player loses
     */
    private void gameOver() {
        gameTimer.stop();
        logger.log("Game lost, Score: "+score);
        JOptionPane.showMessageDialog(this, "Game Over! Score: "+score, "Game Over", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    /**
     * Is called when Player wins the game
     */
    private void victory() {
        gameTimer.stop();
        logger.log("Game won, Score: "+score);
        scoreBoard.recordVictory(username, score);
        JOptionPane.showMessageDialog(this, "Victory! Score: "+score, "Victory", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    /**
     * Moves the player depending on the key pressed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_LEFT) {
            player.moveLeft();
        } else if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
            player.moveRight();
        }
    }

    /**
     * Called when a key is released
     */
    @Override
    public void keyReleased(KeyEvent e) {}

    
    /**
     * Called when a key is typed/pressed and released
     */
    @Override
    public void keyTyped(KeyEvent e) {}
}