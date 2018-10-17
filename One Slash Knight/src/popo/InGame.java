/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package popo;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 *
 * @author yao
 */
public class InGame {

    public int playerx;
    public int playery;
    public boolean playerMoved = false;
    public ArrayList<Enemy> enemy = new ArrayList<Enemy>();
    public boolean[][] dangerArea = new boolean[11][7];
    public boolean hammerCounter = true;
    public int remainEnemy;
    public boolean[][] haveEnemy = new boolean[12][8];
    public int score = 0;
    public int turn;
    public int shield;
    Option op = new Option();

    public void playerMove(KeyEvent e) {
        if (!playerMoved) {
            switch (e.getKeyCode()) {
                case 37:
                    if (playerx > 1) {
                        for (int i = 0; i < enemy.size(); i++) {
                            if ((enemy.get(i).enemyX == playerx - 1) && (enemy.get(i).enemyY == playery)) {
                                playerx++;
                                enemy.remove(i);
                                remainEnemy -= 1;
                                score++;
                            }
                        }
                        playerx -= 1;
                        playerMoved = true;
                    }
                    break;
                case 38:
                    if (playery > 1) {
                        for (int i = 0; i < enemy.size(); i++) {
                            if ((enemy.get(i).enemyX == playerx) && (enemy.get(i).enemyY == playery - 1)) {
                                playery++;
                                enemy.remove(i);
                                remainEnemy -= 1;
                                score++;
                            }
                        }
                        playery -= 1;
                        playerMoved = true;
                    }
                    break;
                case 39:
                    if (playerx < 11) {
                        for (int i = 0; i < enemy.size(); i++) {
                            if ((enemy.get(i).enemyX == playerx + 1) && (enemy.get(i).enemyY == playery)) {
                                playerx--;
                                enemy.remove(i);
                                remainEnemy -= 1;
                                score++;
                            }
                        }
                        playerx += 1;
                        playerMoved = true;
                    }
                    break;
                case 40:
                    if (playery < 7) {
                        for (int i = 0; i < enemy.size(); i++) {
                            if ((enemy.get(i).enemyX == playerx) && (enemy.get(i).enemyY == playery + 1)) {
                                playery--;
                                enemy.remove(i);
                                remainEnemy -= 1;
                                score++;
                            }
                        }
                        playery += 1;
                        playerMoved = true;
                    }
                    break;
                default:
                    break;
            }
        }
    }

    public int enemyMove(int screenMode, int level) {
        if (playerMoved) {
            System.out.println("enemy move");

            showDanger();
            if ((remainEnemy <= 0) && (remainEnemy > -8)) {
                screenMode = -2;
                return screenMode;
            }

            if ((remainEnemy % 6 == -1) && (remainEnemy != -8)) {
                if (shield < 100) {
                    shield++;
                }
                remainEnemy--;
            }

            for (int ix = 0; ix < 11; ix++) {
                for (int iy = 0; iy < 7; iy++) {
                    if ((dangerArea[ix][iy]) && (ix + 1 == playerx) && (iy + 1 == playery)) {
                        if (shield > 0) {
                            shield--;
                        } else {
                            screenMode = -1;
                            if (level == -1) {
                                op.setHighScore(score);
                                System.out.println("getLevel");
                            }
                            return screenMode;
                        }
                    }
                }
            }

            checkEnemy();

            for (int i = 0; i < enemy.size(); i++) {
                if ((enemy.get(i).clas == 1) || (enemy.get(i).clas == 5)) {
                    if ((enemy.get(i).enemyX > playerx) && (enemy.get(i).enemyX - 1 > 0) && (!haveEnemy[enemy.get(i).enemyX - 1][enemy.get(i).enemyY])) {
                        enemy.get(i).enemyX -= 1;
                    } else if ((enemy.get(i).enemyX < playerx) && (enemy.get(i).enemyX + 1 < 12) && (!haveEnemy[enemy.get(i).enemyX + 1][enemy.get(i).enemyY])) {
                        enemy.get(i).enemyX += 1;
                    } else if ((enemy.get(i).enemyY > playery) && (enemy.get(i).enemyY - 1 > 0) && (!haveEnemy[enemy.get(i).enemyX][enemy.get(i).enemyY - 1])) {
                        enemy.get(i).enemyY -= 1;
                    } else if ((enemy.get(i).enemyY < playery) && (enemy.get(i).enemyY + 1 < 8) && (!haveEnemy[enemy.get(i).enemyX][enemy.get(i).enemyY + 1])) {
                        enemy.get(i).enemyY += 1;
                    }
                } else if (enemy.get(i).clas == 2) {
                    if ((enemy.get(i).enemyY > playery) && (enemy.get(i).enemyY - 1 > 0) && (!haveEnemy[enemy.get(i).enemyX][enemy.get(i).enemyY - 1])) {
                        enemy.get(i).enemyY -= 1;
                    } else if ((enemy.get(i).enemyY < playery) && (enemy.get(i).enemyY + 1 < 8) && (!haveEnemy[enemy.get(i).enemyX][enemy.get(i).enemyY + 1])) {
                        enemy.get(i).enemyY += 1;
                    } else if ((enemy.get(i).enemyX > playerx) && (enemy.get(i).enemyX - 1 > 0) && (!haveEnemy[enemy.get(i).enemyX - 1][enemy.get(i).enemyY])) {
                        enemy.get(i).enemyX -= 1;
                    } else if ((enemy.get(i).enemyX < playerx) && (enemy.get(i).enemyX + 1 < 12) && (!haveEnemy[enemy.get(i).enemyX + 1][enemy.get(i).enemyY])) {
                        enemy.get(i).enemyX += 1;
                    }
                } else if (enemy.get(i).clas == 3) {
                    if ((enemy.get(i).enemyX >= playerx) && (enemy.get(i).enemyY < playery)) {
                        if ((enemy.get(i).enemyX > playerx + 1) && (enemy.get(i).enemyX - 1 > 0) && (!haveEnemy[enemy.get(i).enemyX - 1][enemy.get(i).enemyY])) {
                            enemy.get(i).enemyX -= 1;
                        } else if ((enemy.get(i).enemyX < playerx + 1) && (enemy.get(i).enemyX + 1 < 12) && (!haveEnemy[enemy.get(i).enemyX + 1][enemy.get(i).enemyY])) {
                            enemy.get(i).enemyX += 1;
                        } else if ((enemy.get(i).enemyY > playery - 1) && (enemy.get(i).enemyY - 1 > 0) && (!haveEnemy[enemy.get(i).enemyX][enemy.get(i).enemyY - 1])) {
                            enemy.get(i).enemyY -= 1;
                        } else if ((enemy.get(i).enemyY < playery - 1) && (enemy.get(i).enemyY + 1 < 8) && (!haveEnemy[enemy.get(i).enemyX][enemy.get(i).enemyY + 1])) {
                            enemy.get(i).enemyY += 1;
                        }
                    } else if ((enemy.get(i).enemyX > playerx) && (enemy.get(i).enemyY >= playery)) {
                        if ((enemy.get(i).enemyX > playerx + 1) && (enemy.get(i).enemyX - 1 > 0) && (!haveEnemy[enemy.get(i).enemyX - 1][enemy.get(i).enemyY])) {
                            enemy.get(i).enemyX -= 1;
                        } else if ((enemy.get(i).enemyX < playerx + 1) && (enemy.get(i).enemyX + 1 < 12) && (!haveEnemy[enemy.get(i).enemyX + 1][enemy.get(i).enemyY])) {
                            enemy.get(i).enemyX += 1;
                        } else if ((enemy.get(i).enemyY > playery + 1) && (enemy.get(i).enemyY - 1 > 0) && (!haveEnemy[enemy.get(i).enemyX][enemy.get(i).enemyY - 1])) {
                            enemy.get(i).enemyY -= 1;
                        } else if ((enemy.get(i).enemyY < playery + 1) && (enemy.get(i).enemyY + 1 < 8) && (!haveEnemy[enemy.get(i).enemyX][enemy.get(i).enemyY + 1])) {
                            enemy.get(i).enemyY += 1;
                        }
                    } else if ((enemy.get(i).enemyX <= playerx) && (enemy.get(i).enemyY > playery)) {
                        if ((enemy.get(i).enemyX > playerx - 1) && (enemy.get(i).enemyX - 1 > 0) && (!haveEnemy[enemy.get(i).enemyX - 1][enemy.get(i).enemyY])) {
                            enemy.get(i).enemyX -= 1;
                        } else if ((enemy.get(i).enemyX < playerx - 1) && (enemy.get(i).enemyX + 1 < 12) && (!haveEnemy[enemy.get(i).enemyX + 1][enemy.get(i).enemyY])) {
                            enemy.get(i).enemyX += 1;
                        } else if ((enemy.get(i).enemyY > playery + 1) && (enemy.get(i).enemyY - 1 > 0) && (!haveEnemy[enemy.get(i).enemyX][enemy.get(i).enemyY - 1])) {
                            enemy.get(i).enemyY -= 1;
                        } else if ((enemy.get(i).enemyY < playery + 1) && (enemy.get(i).enemyY + 1 < 8) && (!haveEnemy[enemy.get(i).enemyX][enemy.get(i).enemyY + 1])) {
                            enemy.get(i).enemyY += 1;
                        }
                    } else if ((enemy.get(i).enemyX < playerx) && (enemy.get(i).enemyY <= playery)) {
                        if ((enemy.get(i).enemyX > playerx - 1) && (enemy.get(i).enemyX - 1 > 0) && (!haveEnemy[enemy.get(i).enemyX - 1][enemy.get(i).enemyY])) {
                            enemy.get(i).enemyX -= 1;
                        } else if ((enemy.get(i).enemyX < playerx - 1) && (enemy.get(i).enemyX + 1 < 12) && (!haveEnemy[enemy.get(i).enemyX + 1][enemy.get(i).enemyY])) {
                            enemy.get(i).enemyX += 1;
                        } else if ((enemy.get(i).enemyY > playery - 1) && (enemy.get(i).enemyY - 1 > 0) && (!haveEnemy[enemy.get(i).enemyX][enemy.get(i).enemyY - 1])) {
                            enemy.get(i).enemyY -= 1;
                        } else if ((enemy.get(i).enemyY < playery - 1) && (enemy.get(i).enemyY + 1 < 8) && (!haveEnemy[enemy.get(i).enemyX][enemy.get(i).enemyY + 1])) {
                            enemy.get(i).enemyY += 1;
                        }
                    }
                } else if (enemy.get(i).clas == 4) {

                    if ((enemy.get(i).enemyX == playerx + 1) && (enemy.get(i).enemyY == playery) && (enemy.get(i).enemyX + 1 < 12) && (!haveEnemy[enemy.get(i).enemyX + 1][enemy.get(i).enemyY])) {
                        enemy.get(i).enemyX += 1;
                    } else if ((enemy.get(i).enemyX == playerx - 1) && (enemy.get(i).enemyY == playery) && (enemy.get(i).enemyX - 1 > 0) && (!haveEnemy[enemy.get(i).enemyX - 1][enemy.get(i).enemyY])) {
                        enemy.get(i).enemyX -= 1;
                    } else if ((enemy.get(i).enemyX == playerx) && (enemy.get(i).enemyY == playery - 1) && (enemy.get(i).enemyY - 1 > 0) && (!haveEnemy[enemy.get(i).enemyX][enemy.get(i).enemyY - 1])) {
                        enemy.get(i).enemyY -= 1;
                    } else if ((enemy.get(i).enemyX == playerx) && (enemy.get(i).enemyY == playery + 1) && (enemy.get(i).enemyY + 1 < 8) && (!haveEnemy[enemy.get(i).enemyX][enemy.get(i).enemyY + 1])) {
                        enemy.get(i).enemyY += 1;
                    } else if ((enemy.get(i).enemyX > playerx + 2) && (!haveEnemy[enemy.get(i).enemyX - 1][enemy.get(i).enemyY])) {
                        enemy.get(i).enemyX -= 1;
                    } else if ((enemy.get(i).enemyX < playerx - 2) && (!haveEnemy[enemy.get(i).enemyX + 1][enemy.get(i).enemyY])) {
                        enemy.get(i).enemyX += 1;
                    } else if ((enemy.get(i).enemyY > playery + 2) && (!haveEnemy[enemy.get(i).enemyX][enemy.get(i).enemyY - 1])) {
                        enemy.get(i).enemyY -= 1;
                    } else if ((enemy.get(i).enemyY < playery - 2) && (!haveEnemy[enemy.get(i).enemyX][enemy.get(i).enemyY + 1])) {
                        enemy.get(i).enemyY += 1;
                    }
                }
                checkEnemy();
            }
            hammerCounter = !hammerCounter;
            showDanger();

            playerMoved = false;
        }
        return screenMode;
    }

    public void checkEnemy() {
        for (int ix = 1; ix < 12; ix++) {
            for (int iy = 1; iy < 8; iy++) {
                haveEnemy[ix][iy] = false;
            }
        }

        haveEnemy[playerx][playery] = true;
        for (int i = 0; i < enemy.size(); i++) {
            haveEnemy[enemy.get(i).enemyX][enemy.get(i).enemyY] = true;
        }
    }

    public void showDanger() {
        for (int ix = 0; ix < 11; ix++) {
            for (int iy = 0; iy < 7; iy++) {
                dangerArea[ix][iy] = false;
            }
        }

        for (int i = 0; i < enemy.size(); i++) {
            if (enemy.get(i).clas == 1) {
                if ((enemy.get(i).enemyX - 1 > 0)) {
                    dangerArea[enemy.get(i).enemyX - 2][enemy.get(i).enemyY - 1] = true;
                }
                if ((enemy.get(i).enemyX + 1 < 12)) {
                    dangerArea[enemy.get(i).enemyX][enemy.get(i).enemyY - 1] = true;
                }
                if ((enemy.get(i).enemyY - 1 > 0)) {
                    dangerArea[enemy.get(i).enemyX - 1][enemy.get(i).enemyY - 2] = true;
                }
                if ((enemy.get(i).enemyY + 1 < 8)) {
                    dangerArea[enemy.get(i).enemyX - 1][enemy.get(i).enemyY] = true;
                }
            } else if (enemy.get(i).clas == 2) {
                if ((enemy.get(i).enemyX - 1 > 0)) {
                    dangerArea[enemy.get(i).enemyX - 2][enemy.get(i).enemyY - 1] = true;
                }
                if ((enemy.get(i).enemyX + 1 < 12)) {
                    dangerArea[enemy.get(i).enemyX][enemy.get(i).enemyY - 1] = true;
                }
                if ((enemy.get(i).enemyX - 2 > 0)) {
                    dangerArea[enemy.get(i).enemyX - 3][enemy.get(i).enemyY - 1] = true;
                }
                if ((enemy.get(i).enemyX + 2 < 12)) {
                    dangerArea[enemy.get(i).enemyX + 1][enemy.get(i).enemyY - 1] = true;
                }
            } else if (enemy.get(i).clas == 3) {
                if ((enemy.get(i).enemyX - 1 > 0) && (enemy.get(i).enemyY - 1 > 0)) {
                    dangerArea[enemy.get(i).enemyX - 2][enemy.get(i).enemyY - 2] = true;
                }
                if ((enemy.get(i).enemyX + 1 < 12) && (enemy.get(i).enemyY - 1 > 0)) {
                    dangerArea[enemy.get(i).enemyX][enemy.get(i).enemyY - 2] = true;
                }
                if ((enemy.get(i).enemyX - 1 > 0) && (enemy.get(i).enemyY + 1 < 8)) {
                    dangerArea[enemy.get(i).enemyX - 2][enemy.get(i).enemyY] = true;
                }
                if ((enemy.get(i).enemyX + 1 < 12) && (enemy.get(i).enemyY + 1 < 8)) {
                    dangerArea[enemy.get(i).enemyX][enemy.get(i).enemyY] = true;
                }
            } else if (enemy.get(i).clas == 4) {
                if ((enemy.get(i).enemyY - 2 > 0)) {
                    dangerArea[enemy.get(i).enemyX - 1][enemy.get(i).enemyY - 3] = true;
                }
                if ((enemy.get(i).enemyY + 2 < 8)) {
                    dangerArea[enemy.get(i).enemyX - 1][enemy.get(i).enemyY + 1] = true;
                }
                if ((enemy.get(i).enemyX - 2 > 0)) {
                    dangerArea[enemy.get(i).enemyX - 3][enemy.get(i).enemyY - 1] = true;
                }
                if ((enemy.get(i).enemyX + 2 < 12)) {
                    dangerArea[enemy.get(i).enemyX + 1][enemy.get(i).enemyY - 1] = true;
                }
            } else if (enemy.get(i).clas == 5) {
                if (hammerCounter) {
                    if ((enemy.get(i).enemyX - 1 > 0) && (enemy.get(i).enemyY - 1 > 0)) {
                        dangerArea[enemy.get(i).enemyX - 2][enemy.get(i).enemyY - 2] = true;
                    }
                    if ((enemy.get(i).enemyX + 1 < 12) && (enemy.get(i).enemyY - 1 > 0)) {
                        dangerArea[enemy.get(i).enemyX][enemy.get(i).enemyY - 2] = true;
                    }
                    if ((enemy.get(i).enemyX - 1 > 0) && (enemy.get(i).enemyY + 1 < 8)) {
                        dangerArea[enemy.get(i).enemyX - 2][enemy.get(i).enemyY] = true;
                    }
                    if ((enemy.get(i).enemyX + 1 < 12) && (enemy.get(i).enemyY + 1 < 8)) {
                        dangerArea[enemy.get(i).enemyX][enemy.get(i).enemyY] = true;
                    }
                    if ((enemy.get(i).enemyX - 1 > 0)) {
                        dangerArea[enemy.get(i).enemyX - 2][enemy.get(i).enemyY - 1] = true;
                    }
                    if ((enemy.get(i).enemyX + 1 < 12)) {
                        dangerArea[enemy.get(i).enemyX][enemy.get(i).enemyY - 1] = true;
                    }
                    if ((enemy.get(i).enemyY - 1 > 0)) {
                        dangerArea[enemy.get(i).enemyX - 1][enemy.get(i).enemyY - 2] = true;
                    }
                    if ((enemy.get(i).enemyY + 1 < 8)) {
                        dangerArea[enemy.get(i).enemyX - 1][enemy.get(i).enemyY] = true;
                    }
                }
            }
        }
    }

    public void createEnemy(int clas, int type, int enemyX, int enemyY) {
        Enemy newEnemy;
        newEnemy = new Enemy(clas, type, enemyX, enemyY);
        enemy.add(newEnemy);
    }

    public void setLevel(int level) {
        if (level == 1) {
            playerx = 6;
            playery = 4;
            createEnemy(1, 0, 9, 2);
            remainEnemy = 1;
            shield = 0;
        } else if (level == 2) {
            playerx = 6;
            playery = 4;
            createEnemy(1, 4, 4, 4);
            createEnemy(3, 0, 11, 3);
            remainEnemy = 2;
            shield = 0;
        } else if (level == 3) {
            playerx = 6;
            playery = 4;
            createEnemy(1, 3, 3, 4);
            createEnemy(1, 4, 9, 7);
            createEnemy(2, 0, 9, 2);
            remainEnemy = 3;
            shield = 0;
        } else if (level == 4) {
            playerx = 6;
            playery = 4;
            createEnemy(1, 2, 4, 6);
            createEnemy(1, 5, 7, 4);
            createEnemy(3, 6, 7, 5);
            remainEnemy = 3;
            shield = 0;
        } else if (level == 5) {
            playerx = 6;
            playery = 4;
            createEnemy(5, 2, 4, 4);
            createEnemy(5, 7, 10, 4);
            remainEnemy = 2;
            shield = 0;
        } else if (level == 6) {
            playerx = 6;
            playery = 4;
            createEnemy(4, 3, 8, 5);
            createEnemy(1, 9, 9, 6);
            remainEnemy = 2;
            shield = 0;
        } else if (level == 7) {
            playerx = 6;
            playery = 4;
            createEnemy(1, 1, 6, 2);
            createEnemy(1, 6, 6, 6);
            createEnemy(5, 1, 9, 4);
            createEnemy(5, 4, 3, 4);
            remainEnemy = 4;
            shield = 1;
        } else if (level == 8) {
            playerx = 6;
            playery = 4;
            createEnemy(1, 7, 6, 2);
            createEnemy(2, 3, 6, 6);
            createEnemy(3, 0, 8, 4);
            createEnemy(4, 6, 8, 5);
            createEnemy(5, 5, 4, 4);
            remainEnemy = 5;
            shield = 0;
        } else if (level == -1) {
            playerx = 6;
            playery = 4;
            score = 0;
            turn = 0;
            shield = 5;
            remainEnemy = -8;
            infinityCheck();
        }
    }

    public void infinityCheck() {
        if (turn == 0) {
            switch ((int) (Math.random() * 8 + 1)) {
                case 1:
                    if (!haveEnemy[9][2]) {
                        createEnemy((int) (Math.random() * 5 + 1), (int) (Math.random() * 10), 9, 2);
                    }
                    break;
                case 2:
                    if (!haveEnemy[9][4]) {
                        createEnemy((int) (Math.random() * 5 + 1), (int) (Math.random() * 10), 9, 4);
                    }
                    break;
                case 3:
                    if (!haveEnemy[9][6]) {
                        createEnemy((int) (Math.random() * 5 + 1), (int) (Math.random() * 10), 9, 6);
                    }
                    break;
                case 4:
                    if (!haveEnemy[6][2]) {
                        createEnemy((int) (Math.random() * 5 + 1), (int) (Math.random() * 10), 6, 2);
                    }
                    break;
                case 5:
                    if (!haveEnemy[6][6]) {
                        createEnemy((int) (Math.random() * 5 + 1), (int) (Math.random() * 10), 6, 6);
                    }
                    break;
                case 6:
                    if (!haveEnemy[3][2]) {
                        createEnemy((int) (Math.random() * 5 + 1), (int) (Math.random() * 10), 3, 2);
                    }
                    break;
                case 7:
                    if (!haveEnemy[3][4]) {
                        createEnemy((int) (Math.random() * 5 + 1), (int) (Math.random() * 10), 3, 4);
                    }
                    break;
                case 8:
                    if (!haveEnemy[3][6]) {
                        createEnemy((int) (Math.random() * 5 + 1), (int) (Math.random() * 10), 3, 6);
                    }
                    break;
            }
        }
        turn++;
        if (turn > 2) {
            turn = 0;
        }
        showDanger();
    }

    public void resetGame() {
        enemy.clear();
        playerMoved = false;
        hammerCounter = true;
        score = 0;
        remainEnemy = 0;
    }
}

class Enemy {

    int clas, type, enemyX, enemyY;

    /**
     *
     * @param _clas for enemy's class 1: fighter 4 8 6 2 2: lancer left 2 right
     * 2 3: magician 7 9 1 3 4: Archer 8 4 2 6 +1 5: Hammer 78941236 1cd
     * @param _type
     * @param _enemyX
     * @param _enemyY
     */
    Enemy(int _clas, int _type, int _enemyX, int _enemyY) {
        clas = _clas;
        type = _type;
        enemyX = _enemyX;
        enemyY = _enemyY;
    }
}
