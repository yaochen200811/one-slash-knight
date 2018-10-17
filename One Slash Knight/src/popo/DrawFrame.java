package popo;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yao
 */
public class DrawFrame extends Frame {

    Image backFloor = GameUtil.getImage("image/backFloor.png");
    Image attackWarning = GameUtil.getImage("image/attackWarning.png");
    Image titleScreen = GameUtil.getImage("image/titleScreen.png");
    Image optionScreen = GameUtil.getImage("image/optionScreen.png");
    Image chooseScreen = GameUtil.getImage("image/chooseScreen.png");
    Image helpScreen = GameUtil.getImage("image/helpScreen.png");
    Image arrow = GameUtil.getImage("image/arrow.png");
    Image arrow2 = GameUtil.getImage("image/arrow2.png");
    Image chain = GameUtil.getImage("image/chain.png");
    Image chainL = GameUtil.getImage("image/chainL.png");
    Image level1 = GameUtil.getImage("image/level1.png");
    Image level2 = GameUtil.getImage("image/level2.png");
    Image level3 = GameUtil.getImage("image/level3.png");
    Image level4 = GameUtil.getImage("image/level4.png");
    Image level5 = GameUtil.getImage("image/level5.png");
    Image level6 = GameUtil.getImage("image/level6.png");
    Image level7 = GameUtil.getImage("image/level7.png");
    Image level8 = GameUtil.getImage("image/level8.png");
    Image scoreboard = GameUtil.getImage("image/scoreboard.png");
    Image scoreboard2 = GameUtil.getImage("image/scoreboard2.png");
    Image safeBack = GameUtil.getImage("image/safeBack.png");
    Image dangerBack = GameUtil.getImage("image/dangerBack.png");
    Image died = GameUtil.getImage("image/died.png");
    Image changeScreenT = GameUtil.getImage("image/changeScreenT.png");
    Image changeScreenD = GameUtil.getImage("image/changeScreenD.png");
    Image victory = GameUtil.getImage("image/victory.png");
    Image infinityTitle = GameUtil.getImage("image/infinityTitle.png");
    Image lockedS = GameUtil.getImage("image/character/lockedS.png");
    Image starTitle = GameUtil.getImage("image/star title.jpg");
    Image starTitle1 = GameUtil.getImage("image/star title white1.jpg");
    Image starTitle2 = GameUtil.getImage("image/star title white2.jpg");

    ArrayList<Image> levelTitle = new ArrayList<Image>();
    Image[][] characterIcon = new Image[6][15];
    Image[] numPic = new Image[11];

    Font scoreFont = new Font("Comic Sans MS", 1, 28);
    Font unlockFont = new Font("Comic Sans MS", 1, 40);

    BufferedImage image = new BufferedImage(1000, 600, BufferedImage.TYPE_INT_RGB);
    Graphics g = image.getGraphics();
    private Image offScreenImage;
    public int screenMode = 9;
    public int arrowPosition = 1;
    public boolean keyDown = false;
    public int level = 0;
    InGame ig = new InGame();
    static Option op = new Option();
    public int changeT, changeD;
    public int changeAble = 0;
    public int requirStop = 0;
    public int counter=0;

    public void launchFrame() {
        setSize(1000, 600);
        setLocation(100, 100);
        setVisible(true);

        new PaintThread().start();

        addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        addKeyListener(new KeyMonitor());
    }

    public void paint(Graphics g) {
        switch (screenMode) {
            case 9:{
                if (counter==0){
                    counter++;
                    requirStop=500;
                }else if (counter==1){
                    g.drawImage(starTitle2, 368, 220, null);
                    counter++;
                    requirStop=500;
                }else if (counter==2){
                    g.drawImage(starTitle1, 368, 220, null);
                    counter++;
                    requirStop=500;
                }else if (counter==3){
                    g.drawImage(starTitle, 368, 220, null);
                    counter++;
                    requirStop=500;
                }else if (counter==4){
                    counter++;
                    requirStop=500;
                }else if (changeAble == 0){
                    op.getIcon();
                    changeAble=8;
                    changeT = -350;
                    changeD = 474;
                }
                
                if (changeAble > 0) {
                    if (changeT > 0) {
                        screenMode = changeAble;
                        g.drawImage(changeScreenT, 0, 0, null);
                        g.drawImage(changeScreenD, 0, 0, null);
                        changeT = 0;
                        changeD = 0;
                        changeAble = -1;
                        requirStop = 1000;
                        arrowPosition = 1;
                    }
                    g.drawImage(changeScreenT, 0, changeT, null);
                    g.drawImage(changeScreenD, 0, changeD, null);
                    changeT += 20;
                    changeD -= 27;
                }
                break;
            }
            case 8: {
                g.drawImage(titleScreen, 0, 0, null);
                if (arrowPosition == 1) {
                    g.drawImage(arrow, 390, 215, null);
                } else if (arrowPosition == 2) {
                    g.drawImage(arrow, 500, 310, null);
                } else if (arrowPosition == 3) {
                    g.drawImage(arrow, 400, 410, null);
                } else if (arrowPosition == 4) {
                    g.drawImage(arrow, 370, 500, null);
                }

                if (changeAble == -1) {
                    if (changeT < -350) {
                        changeAble = 0;
                    }
                    g.drawImage(changeScreenT, 0, changeT, null);
                    g.drawImage(changeScreenD, 0, changeD, null);
                    changeT -= 20;
                    changeD += 27;
                }

                if (changeAble > 0) {
                    if (changeT > 0) {
                        screenMode = changeAble;
                        g.drawImage(changeScreenT, 0, 0, null);
                        g.drawImage(changeScreenD, 0, 0, null);
                        changeT = 0;
                        changeD = 0;
                        changeAble = -1;
                        requirStop = 500;
                        arrowPosition = 1;
                    }
                    g.drawImage(changeScreenT, 0, changeT, null);
                    g.drawImage(changeScreenD, 0, changeD, null);
                    changeT += 20;
                    changeD -= 27;
                }
            }
            break;
            case 1: {
                for (int x = 0; x < 15; x++) {
                    for (int y = 0; y < 9; y++) {
                        g.drawImage(backFloor, x * 70 - 15, y * 70 - 5, null);
                    }
                }

                for (int i = 0; i < 5; i++) {
                    g.drawImage(chain, 6, i * 225, null);
                    g.drawImage(chain, 970, i * 225, null);
                }

                for (int i = 0; i < 15; i++) {
                    g.drawImage(chainL, i * 225, 30, null);
                    g.drawImage(chainL, i * 225, 570, null);
                }

                g.drawImage(level1, 180, 140, null);
                g.drawImage(level2, 180, 240, null);
                g.drawImage(level3, 180, 340, null);
                g.drawImage(level4, 180, 440, null);
                g.drawImage(level5, 648, 140, null);
                g.drawImage(level6, 648, 240, null);
                g.drawImage(level7, 648, 340, null);
                g.drawImage(level8, 648, 440, null);
                

                if (arrowPosition == 1) {
                    g.drawImage(arrow, 370, 160, null);
                }else if (arrowPosition == 2) {
                    g.drawImage(arrow, 370, 260, null);
                }else if (arrowPosition == 3) {
                    g.drawImage(arrow, 370, 360, null);
                }else if (arrowPosition == 4) {
                    g.drawImage(arrow, 370, 460, null);
                }else if (arrowPosition == 5) {
                    g.drawImage(arrow, 838, 160, null);
                }else if (arrowPosition == 6) {
                    g.drawImage(arrow, 838, 260, null);
                }else if (arrowPosition == 7) {
                    g.drawImage(arrow, 838, 360, null);
                }else if (arrowPosition == 8) {
                    g.drawImage(arrow, 838, 460, null);
                }

                if (changeAble == -1) {
                    if (changeT < -350) {
                        changeAble = 0;
                    }
                    g.drawImage(changeScreenT, 0, changeT, null);
                    g.drawImage(changeScreenD, 0, changeD, null);
                    changeT -= 20;
                    changeD += 27;
                }

                if (changeAble > 0) {
                    if (changeT > 0) {
                        screenMode = changeAble;
                        g.drawImage(changeScreenT, 0, 0, null);
                        g.drawImage(changeScreenD, 0, 0, null);
                        changeT = 0;
                        changeD = 0;
                        changeAble = -1;
                        requirStop = 500;
                        arrowPosition = 1;
                    }
                    g.drawImage(changeScreenT, 0, changeT, null);
                    g.drawImage(changeScreenD, 0, changeD, null);
                    changeT += 20;
                    changeD -= 27;
                }

                break;
            }
            case 3: {
                g.drawImage(optionScreen, 0, 0, null);
                g.setFont(scoreFont);
                g.setColor(Color.black);
                for (int i = 0; i < 3; i++) {
                    g.drawString((i + 1) + ". " + op.highScore[i], 750, 250 + i * 70);
                }
                if (arrowPosition == 1) {
                    g.drawImage(arrow, 540, 220, null);
                } else if (arrowPosition == 2) {
                    g.drawImage(arrow, 335, 355, null);
                }
                if (changeAble == -1) {
                    if (changeT < -350) {
                        changeAble = 0;
                    }
                    g.drawImage(changeScreenT, 0, changeT, null);
                    g.drawImage(changeScreenD, 0, changeD, null);
                    changeT -= 20;
                    changeD += 27;
                }

                if (changeAble > 0) {
                    if (changeT > 0) {
                        screenMode = changeAble;
                        g.drawImage(changeScreenT, 0, 0, null);
                        g.drawImage(changeScreenD, 0, 0, null);
                        changeT = 0;
                        changeD = 0;
                        changeAble = -1;
                        requirStop = 500;
                        arrowPosition = 1;
                    }
                    g.drawImage(changeScreenT, 0, changeT, null);
                    g.drawImage(changeScreenD, 0, changeD, null);
                    changeT += 20;
                    changeD -= 27;
                }
                break;
            }
            case 4: {
                g.drawImage(chooseScreen, 0, 0, null);
                g.drawImage(characterIcon[0][0], 260, 200, null);

                if (op.lockState[12]) {
                    g.drawImage(characterIcon[0][1], 340, 200, null);
                } else {
                    g.drawImage(lockedS, 340, 200, null);
                }

                for (int y = 0; y < 2; y++) {
                    for (int x = 0; x < 6; x++) {
                        if (op.lockState[y * 6 + x]) {
                            g.drawImage(characterIcon[0][y * 6 + x + 2], 100 + x * 80, 320 + y * 120, null);
                        } else {
                            g.drawImage(lockedS, 100 + x * 80, 320 + y * 120, null);
                        }
                    }
                }
                if (arrowPosition == 1) {
                    g.drawImage(arrow2, 269, 260, null);
                } else if (arrowPosition == 2) {
                    g.drawImage(arrow2, 349, 260, null);
                } else {
                    g.drawImage(arrow2, 109 + ((arrowPosition - 3) % 6) * 80, 380 + (int) ((arrowPosition - 3) / 6) * 120, null);
                }

                if (changeAble == -1) {
                    if (changeT < -350) {
                        changeAble = 0;
                    }
                    g.drawImage(changeScreenT, 0, changeT, null);
                    g.drawImage(changeScreenD, 0, changeD, null);
                    changeT -= 20;
                    changeD += 27;
                }

                if (changeAble > 0) {
                    if (changeT > 0) {
                        screenMode = changeAble;
                        g.drawImage(changeScreenT, 0, 0, null);
                        g.drawImage(changeScreenD, 0, 0, null);
                        changeT = 0;
                        changeD = 0;
                        changeAble = -1;
                        requirStop = 500;
                        arrowPosition = 1;
                    }
                    g.drawImage(changeScreenT, 0, changeT, null);
                    g.drawImage(changeScreenD, 0, changeD, null);
                    changeT += 20;
                    changeD -= 27;
                }
                break;
            }
            case 5: {
                g.drawImage(helpScreen, 0, 0, null);
                if (changeAble == -1) {
                    if (changeT < -350) {
                        changeAble = 0;
                    }
                    g.drawImage(changeScreenT, 0, changeT, null);
                    g.drawImage(changeScreenD, 0, changeD, null);
                    changeT -= 20;
                    changeD += 27;
                }

                if (changeAble > 0) {
                    if (changeT > 0) {
                        screenMode = changeAble;
                        g.drawImage(changeScreenT, 0, 0, null);
                        g.drawImage(changeScreenD, 0, 0, null);
                        changeT = 0;
                        changeD = 0;
                        changeAble = -1;
                        requirStop = 500;
                        arrowPosition = 1;
                    }
                    g.drawImage(changeScreenT, 0, changeT, null);
                    g.drawImage(changeScreenD, 0, changeD, null);
                    changeT += 20;
                    changeD -= 27;
                }
                break;
            }
            case -2:
            case -1:
            case 2: {
                for (int x = 0; x < 15; x++) {
                    for (int y = 0; y < 9; y++) {
                        g.drawImage(backFloor, x * 70 - 15, y * 70 - 5, null);
                        if ((x > 0) && (x < 12) && (y > 0) && (y < 8)) {
                            if (ig.dangerArea[x - 1][y - 1]) {
                                g.drawImage(dangerBack, x * 70 - 15, y * 70 - 5, null);
                            } else {
                                g.drawImage(safeBack, x * 70 - 15, y * 70 - 5, null);
                            }
                        }
                    }
                }

                for (int i = 0; i < 5; i++) {
                    g.drawImage(chain, 6, i * 225, null);
                }

                for (int i = 0; i < 15; i++) {
                    g.drawImage(chainL, i * 225, 30, null);
                    g.drawImage(chainL, i * 225, 570, null);
                }

                g.drawImage(chain, 850, -50, null);
                g.drawImage(chain, 945, -50, null);
                g.drawImage(chain, 850, 550, null);
                g.drawImage(chain, 945, 550, null);
                if (level > 0) {
                    g.drawImage(scoreboard, 845, 55, null);
                    g.drawImage(levelTitle.get(level - 1), 845, 55, null);
                    if (ig.shield > 0) {
                        g.drawImage(numPic[(int) (ig.shield / 10)], 863, 381, null);
                        g.drawImage(numPic[(int) (ig.shield % 10)], 913, 381, null);
                    } else {
                        g.drawImage(numPic[10], 863, 381, null);
                        g.drawImage(numPic[10], 913, 381, null);
                    }
                    if (ig.remainEnemy > 0) {
                        g.drawImage(numPic[(int) (ig.remainEnemy / 10)], 863, 503, null);
                        g.drawImage(numPic[(int) (ig.remainEnemy % 10)], 913, 503, null);
                    } else {
                        g.drawImage(numPic[10], 863, 503, null);
                        g.drawImage(numPic[10], 913, 503, null);
                    }
                } else {
                    g.drawImage(scoreboard2, 845, 55, null);
                    g.drawImage(infinityTitle, 845, 55, null);
                    if (ig.shield > 0) {
                        g.drawImage(numPic[(int) (ig.shield / 10)], 863, 381, null);
                        g.drawImage(numPic[(int) (ig.shield % 10)], 913, 381, null);
                    } else {
                        g.drawImage(numPic[10], 863, 381, null);
                        g.drawImage(numPic[10], 913, 381, null);
                    }
                    if (ig.score > 0) {
                        g.drawImage(numPic[(int) (ig.score / 100)], 845, 503, null);
                        g.drawImage(numPic[(int) (ig.score / 10)], 890, 503, null);
                        g.drawImage(numPic[(int) (ig.score % 10)], 935, 503, null);
                    } else {
                        g.drawImage(numPic[10], 845, 503, null);
                        g.drawImage(numPic[10], 890, 503, null);
                        g.drawImage(numPic[10], 935, 503, null);
                    }
                }

                g.drawImage(characterIcon[0][op.mainIcon], ig.playerx * 70 - 5, ig.playery * 70 + 5, null);

                for (int i = 0; i < ig.enemy.size(); i++) {
                    g.drawImage(characterIcon[ig.enemy.get(i).clas][ig.enemy.get(i).type], ig.enemy.get(i).enemyX * 70 - 5, ig.enemy.get(i).enemyY * 70 + 5, null);
                }

                if (screenMode == -1) {
                    g.drawImage(died, 0, 0, null);
                    if (level == -1) {
                        g.setFont(unlockFont);
                        g.setColor(Color.yellow);
                        if (op.unlockNum > 0) {
                            g.drawString("You Unlocked " + op.unlockNum + " New Character(s)", 180, 420);
                        }
                    }
                } else if (screenMode == -2) {
                    g.drawImage(victory, 0, 0, null);

                }

                if (changeAble == -1) {

                    g.drawImage(changeScreenT, 0, changeT, null);
                    g.drawImage(changeScreenD, 0, changeD, null);
                    changeT -= 20;
                    changeD += 27;
                    if (changeT < -350) {
                        changeAble = 0;
                    }
                }

                if (changeAble > 0) {
                    if (changeT > 0) {
                        screenMode = changeAble;
                        g.drawImage(changeScreenT, 0, 0, null);
                        g.drawImage(changeScreenD, 0, 0, null);
                        changeT = 0;
                        changeD = 0;
                        changeAble = -1;
                        ig.resetGame();
                        requirStop = 500;
                        arrowPosition = 1;
                    }
                    g.drawImage(changeScreenT, 0, changeT, null);
                    g.drawImage(changeScreenD, 0, changeD, null);
                    changeT += 20;
                    changeD -= 27;
                }
            }
        }
    }

    class PaintThread extends Thread {

        public void run() {
            while (true) {
                repaint();
                try {
                    Thread.sleep(30);
                } catch (InterruptedException ex) {
                    Logger.getLogger(DrawFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (requirStop != 0) {
                    try {
                        Thread.sleep(requirStop);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(DrawFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    requirStop = 0;
                }
            }
        }
    }

    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(1000, 600);
        }
        Graphics gImage = offScreenImage.getGraphics();
        paint(gImage);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    public static void main(String[] args) {
        DrawFrame df = new DrawFrame();
        df.launchFrame();
        op.getHighScore();
        df.levelTitle.add(GameUtil.getImage("image/level1Title.png"));
        df.levelTitle.add(GameUtil.getImage("image/level2Title.png"));
        df.levelTitle.add(GameUtil.getImage("image/level3Title.png"));
        df.levelTitle.add(GameUtil.getImage("image/level4Title.png"));
        df.levelTitle.add(GameUtil.getImage("image/level5Title.png"));
        df.levelTitle.add(GameUtil.getImage("image/level6Title.png"));
        df.levelTitle.add(GameUtil.getImage("image/level7Title.png"));
        df.levelTitle.add(GameUtil.getImage("image/level8Title.png"));
        df.numPic[0] = GameUtil.getImage("image/num0.png");
        df.numPic[1] = GameUtil.getImage("image/num1.png");
        df.numPic[2] = GameUtil.getImage("image/num2.png");
        df.numPic[3] = GameUtil.getImage("image/num3.png");
        df.numPic[4] = GameUtil.getImage("image/num4.png");
        df.numPic[5] = GameUtil.getImage("image/num5.png");
        df.numPic[6] = GameUtil.getImage("image/num6.png");
        df.numPic[7] = GameUtil.getImage("image/num7.png");
        df.numPic[8] = GameUtil.getImage("image/num8.png");
        df.numPic[9] = GameUtil.getImage("image/num9.png");
        df.numPic[10] = GameUtil.getImage("image/nullNum.png");
        df.characterIcon[0][0] = GameUtil.getImage("image/character/protagonistS.png");
        df.characterIcon[0][1] = GameUtil.getImage("image/character/shiZiS.png");
        df.characterIcon[0][2] = GameUtil.getImage("image/character/guoDongS.png");
        df.characterIcon[0][3] = GameUtil.getImage("image/character/jiangShiS.png");
        df.characterIcon[0][4] = GameUtil.getImage("image/character/jiQiS.png");
        df.characterIcon[0][5] = GameUtil.getImage("image/character/kaLianS.png");
        df.characterIcon[0][6] = GameUtil.getImage("image/character/longHuangS.png");
        df.characterIcon[0][7] = GameUtil.getImage("image/character/maoS.png");
        df.characterIcon[0][8] = GameUtil.getImage("image/character/wangZiS.png");
        df.characterIcon[0][9] = GameUtil.getImage("image/character/No1S.png");
        df.characterIcon[0][10] = GameUtil.getImage("image/character/teLaiToS.png");
        df.characterIcon[0][11] = GameUtil.getImage("image/character/nanaS.png");
        df.characterIcon[0][12] = GameUtil.getImage("image/character/xinCunS.png");
        df.characterIcon[0][13] = GameUtil.getImage("image/character/zuZhangS.png");
        df.characterIcon[1][0] = GameUtil.getImage("image/character/dingGongF.png");
        df.characterIcon[1][1] = GameUtil.getImage("image/character/ghoustF.png");
        df.characterIcon[1][2] = GameUtil.getImage("image/character/huiZhangP.png");
        df.characterIcon[1][3] = GameUtil.getImage("image/character/leiHuangP.png");
        df.characterIcon[1][4] = GameUtil.getImage("image/character/liangShangP.png");
        df.characterIcon[1][5] = GameUtil.getImage("image/character/qiYaP.png");
        df.characterIcon[1][6] = GameUtil.getImage("image/character/riTianP.png");
        df.characterIcon[1][7] = GameUtil.getImage("image/character/shiZiP.png");
        df.characterIcon[1][8] = GameUtil.getImage("image/character/xiaoJieP.png");
        df.characterIcon[1][9] = GameUtil.getImage("image/character/maoP.png");
        df.characterIcon[2][0] = GameUtil.getImage("image/character/keiL.png");
        df.characterIcon[2][1] = GameUtil.getImage("image/character/buLaiTeL.png");
        df.characterIcon[2][2] = GameUtil.getImage("image/character/gegeL.png");
        df.characterIcon[2][3] = GameUtil.getImage("image/character/guangL.png");
        df.characterIcon[2][4] = GameUtil.getImage("image/character/hanBaoL.png");
        df.characterIcon[2][5] = GameUtil.getImage("image/character/leiNaL.png");
        df.characterIcon[2][6] = GameUtil.getImage("image/character/seiXiLiYaL.png");
        df.characterIcon[2][7] = GameUtil.getImage("image/character/taiQiuL.png");
        df.characterIcon[2][8] = GameUtil.getImage("image/character/tianShiL.png");
        df.characterIcon[2][9] = GameUtil.getImage("image/character/xiXueGuiL.png");
        df.characterIcon[3][0] = GameUtil.getImage("image/character/xiaM.png");
        df.characterIcon[3][1] = GameUtil.getImage("image/character/daHaoM.png");
        df.characterIcon[3][2] = GameUtil.getImage("image/character/kaLianM.png");
        df.characterIcon[3][3] = GameUtil.getImage("image/character/shengNvM.png");
        df.characterIcon[3][4] = GameUtil.getImage("image/character/xingLiM.png");
        df.characterIcon[3][5] = GameUtil.getImage("image/character/xiongM.png");
        df.characterIcon[3][6] = GameUtil.getImage("image/character/xiSuoM.png");
        df.characterIcon[3][7] = GameUtil.getImage("image/character/yangLiM.png");
        df.characterIcon[3][8] = GameUtil.getImage("image/character/yuM.png");
        df.characterIcon[3][9] = GameUtil.getImage("image/character/zhanDongM.png");
        df.characterIcon[4][0] = GameUtil.getImage("image/character/yangLiA.png");
        df.characterIcon[4][1] = GameUtil.getImage("image/character/baoZiA.png");
        df.characterIcon[4][2] = GameUtil.getImage("image/character/huiZhangA.png");
        df.characterIcon[4][3] = GameUtil.getImage("image/character/maoA.png");
        df.characterIcon[4][4] = GameUtil.getImage("image/character/nanaA.png");
        df.characterIcon[4][5] = GameUtil.getImage("image/character/qingA.png");
        df.characterIcon[4][6] = GameUtil.getImage("image/character/sheA.png");
        df.characterIcon[4][7] = GameUtil.getImage("image/character/xiaA.png");
        df.characterIcon[4][8] = GameUtil.getImage("image/character/yuanYiA.png");
        df.characterIcon[4][9] = GameUtil.getImage("image/character/zhaKeA.png");
        df.characterIcon[5][0] = GameUtil.getImage("image/character/leiEnH.png");
        df.characterIcon[5][1] = GameUtil.getImage("image/character/buLaiTeH.png");
        df.characterIcon[5][2] = GameUtil.getImage("image/character/chaXiongH.png");
        df.characterIcon[5][3] = GameUtil.getImage("image/character/chuXingH.png");
        df.characterIcon[5][4] = GameUtil.getImage("image/character/duanZaoH.png");
        df.characterIcon[5][5] = GameUtil.getImage("image/character/huoLongH.png");
        df.characterIcon[5][6] = GameUtil.getImage("image/character/tangH.png");
        df.characterIcon[5][7] = GameUtil.getImage("image/character/zhongH.png");
        df.characterIcon[5][8] = GameUtil.getImage("image/character/haiOuH.png");
        df.characterIcon[5][9] = GameUtil.getImage("image/character/renYuH.png");
    }

    class KeyMonitor extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            System.out.println("R: " + e.getKeyCode());
            keyDown = false;
        }

        @Override
        public void keyPressed(KeyEvent e) {
            System.out.println("P: " + e.getKeyCode());
            if ((!keyDown) && (changeAble == 0)) {
                switch (screenMode) {
                    case -2:
                    case -1:
                        switch (e.getKeyCode()) {
                            case 10:
                            case 8: {
                                if (level == -1) {
                                    changeAble = 8;
                                } else {
                                    changeAble = 1;
                                }
                                changeT = -350;
                                changeD = 474;
                                //arrowPosition = 1;
                                break;
                            }
                        }
                        break;
                    case 8:
                        switch (e.getKeyCode()) {
                            case 38:
                                if (arrowPosition > 1) {
                                    arrowPosition -= 1;
                                }
                                break;
                            case 40:
                                if (arrowPosition < 4) {
                                    arrowPosition += 1;
                                }
                                break;
                            case 10:
                                switch (arrowPosition) {
                                    case 1:
                                        changeAble = 1;
                                        changeT = -350;
                                        changeD = 474;
                                        break;
                                    case 2:
                                        changeAble = 2;
                                        changeT = -350;
                                        changeD = 474;
                                        level = -1;
                                        ig.setLevel(level);
                                        break;
                                    case 3:
                                        changeAble = 3;
                                        changeT = -350;
                                        changeD = 474;
                                        op.getHighScore();
                                        break;
                                    case 4:
                                        System.exit(0);
                                        break;
                                    default:
                                        break;
                                }
                                break;
                            default:
                                break;
                        }
                        break;
                    case 1:
                        switch (e.getKeyCode()) {
                            case 37:
                                if (arrowPosition > 4) {
                                    arrowPosition -= 4;
                                }
                                break;
                            case 39:
                                if (arrowPosition < 5) {
                                    arrowPosition += 4;
                                }
                                break;
                            case 38:
                                if (arrowPosition > 1) {
                                    arrowPosition -= 1;
                                }
                                break;
                            case 40:
                                if (arrowPosition < 8) {
                                    arrowPosition += 1;
                                }
                                break;
                            case 8: {
                                changeAble = 8;
                                changeT = -350;
                                changeD = 474;
                                //arrowPosition = 1;
                                break;
                            }
                            case 10:
                                //**************************************************************add level*************************************************************
                                changeAble = 2;
                                changeT = -350;
                                changeD = 474;
                                level = arrowPosition;
                                ig.setLevel(level);
                                //arrowPosition = 1;
                                ig.showDanger();
                                break;
                        }
                        break;

                    case 3:
                        switch (e.getKeyCode()) {
                            case 38:
                                if (arrowPosition > 1) {
                                    arrowPosition -= 1;
                                }
                                break;
                            case 40:
                                if (arrowPosition < 2) {
                                    arrowPosition += 1;
                                }
                                break;
                            case 8: {
                                changeAble = 8;
                                changeT = -350;
                                changeD = 474;
                                break;
                            }
                            case 10:
                                switch (arrowPosition) {
                                    case 1:
                                        changeAble = 4;
                                        changeT = -350;
                                        changeD = 474;
                                        break;
                                    case 2:
                                        changeAble = 5;
                                        changeT = -350;
                                        changeD = 474;
                                        break;
                                    default:
                                        break;
                                }
                                break;
                            default:
                                break;
                        }
                        break;
                    case 4:
                        switch (e.getKeyCode()) {
                            case 37:
                                if (arrowPosition > 1) {
                                    arrowPosition -= 1;
                                }
                                break;
                            case 39:
                                if (arrowPosition < 14) {
                                    arrowPosition += 1;
                                }
                                break;
                            case 38:
                                if ((arrowPosition >= 3) && (arrowPosition <= 5)) {
                                    arrowPosition = 1;
                                } else if ((arrowPosition >= 6) && (arrowPosition <= 8)) {
                                    arrowPosition = 2;
                                } else if ((arrowPosition >= 9) && (arrowPosition <= 14)) {
                                    arrowPosition -= 6;
                                }
                                break;
                            case 40:
                                if ((arrowPosition == 1) || (arrowPosition == 2)) {
                                    arrowPosition += 4;
                                } else {
                                    if ((int) ((arrowPosition - 3) / 6) + 1 < 2) {
                                        arrowPosition += 6;
                                    }
                                }
                                break;
                            case 8: {
                                changeAble = 3;
                                changeT = -350;
                                changeD = 474;
                                break;
                            }
                            case 10:
                                op.mainIcon=arrowPosition-1;
                                op.setIcon();
                                changeAble = 8;
                                changeT = -350;
                                changeD = 474;
                                break;
                            default:
                                break;
                        }
                        System.out.println(arrowPosition);
                        break;

                    case 5:
                        switch (e.getKeyCode()) {
                            case 8: {
                                changeAble = 3;
                                changeT = -350;
                                changeD = 474;
                                break;
                            }
                            default:
                                break;
                        }
                        break;
                    case 2: {

                        ig.playerMove(e);
                        screenMode = ig.enemyMove(screenMode, level);

                        if (level == -1) {
                            ig.infinityCheck();
                        }

                        if (e.getKeyCode() == 8) {
                            if (level == -1) {
                                changeAble = 8;
                            } else {
                                changeAble = 1;
                            }
                            changeT = -350;
                            changeD = 474;
                            //arrowPosition = 1;
                            break;
                        }
                    }
                    default:
                        break;
                }
                keyDown = true;
            }

        }

    }

}
