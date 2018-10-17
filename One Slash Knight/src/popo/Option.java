/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package popo;

import java.io.File;
import java.io.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yao
 */
public class Option {

    public static int mainIcon = 0;
    public String[] highScore = new String[3];
    public String lockCharacter;
    public boolean[] lockState = new boolean[13];
    private int tempRandom;
    private int lockNum;
    public static int unlockNum;

    public void getHighScore() {
        try {
            String pathname = ".//save.sav";
            File filename = new File(pathname);
            InputStreamReader reader = new InputStreamReader(
                    new FileInputStream(filename));
            BufferedReader br = new BufferedReader(reader);
            String line = "";
            for (int i = 0; i < 3; i++) {
                highScore[i] = br.readLine();
            }
            lockCharacter = br.readLine();
            mainIcon = Integer.parseInt(br.readLine());
            br.close();
        } catch (IOException ex) {
            Logger.getLogger(Option.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int i = 12; i > 0; i--) {
            lockState[i] = ((Long.parseLong(lockCharacter) % ((long) (Math.pow(10, i + 1)))) >= (Math.pow(10, i)));
        }
        lockState[0] = (((Long.parseLong(lockCharacter)) % (10)) == 1);
    }

    public void setHighScore(int score) {
        try {
            String pathname = ".//save.sav";
            File filename = new File(pathname);
            InputStreamReader reader = new InputStreamReader(
                    new FileInputStream(filename));
            BufferedReader br = new BufferedReader(reader);
            for (int i = 0; i < 3; i++) {
                highScore[i] = br.readLine();
            }
            lockCharacter = br.readLine();
            br.close();
        } catch (IOException ex) {
            Logger.getLogger(Option.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int i = 12; i > 0; i--) {
            lockState[i] = ((Long.parseLong(lockCharacter) % ((long) (Math.pow(10, i + 1)))) >= (Math.pow(10, i)));
        }
        lockState[0] = (((Long.parseLong(lockCharacter)) % (10)) == 1);

        System.out.println(score);
        if (score >= Integer.parseInt(highScore[0])) {
            highScore[2] = highScore[1];
            highScore[1] = highScore[0];
            highScore[0] = Integer.toString(score);
        } else if (score >= Integer.parseInt(highScore[1])) {
            highScore[2] = highScore[1];
            highScore[1] = Integer.toString(score);
        } else if (score >= Integer.parseInt(highScore[2])) {
            highScore[2] = Integer.toString(score);
        }

        lockNum = 0;
        unlockNum = 0;
        System.out.println("getUnlock");
        for (int i = 0; i < 13; i++) {
            if (lockState[i]) {
                lockNum++;
            }
        }

        while ((score >= 20) && (lockNum < 13)) {
            tempRandom = (int) (Math.random() * 13);
            if (!lockState[tempRandom]) {
                lockState[tempRandom] = true;
                score -= 20;
                lockNum++;
                unlockNum++;
            }
        }

        lockCharacter = "";
        for (int i = 12; i >= 0; i--) {
            if (lockState[i]) {
                lockCharacter = lockCharacter + 1;
            } else {
                lockCharacter = lockCharacter + 0;
            }
        }
        //lockCharacter=Long.toString(Long.parseLong(lockCharacter))+Math.pow(10, (int)(Math.random()*13));

        try {
            String pathname = ".//save.sav";
            File filename = new File(pathname);
            OutputStreamWriter writer = new OutputStreamWriter(
                    new FileOutputStream(filename));
            BufferedWriter bw = new BufferedWriter(writer);
            for (int i = 0; i < 3; i++) {
                bw.write(highScore[i] + "\n");
            }
            bw.write(lockCharacter + "\n");
            bw.write(Integer.toString(mainIcon));
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(Option.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setIcon() {
        try {
            String pathname = ".//save.sav";
            File filename = new File(pathname);
            InputStreamReader reader = new InputStreamReader(
                    new FileInputStream(filename));
            BufferedReader br = new BufferedReader(reader);
            for (int i = 0; i < 3; i++) {
                highScore[i] = br.readLine();
            }
            lockCharacter = br.readLine();
            br.close();
        } catch (IOException ex) {
            Logger.getLogger(Option.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int i = 12; i > 0; i--) {
            lockState[i] = ((Long.parseLong(lockCharacter) % ((long) (Math.pow(10, i + 1)))) >= (Math.pow(10, i)));
        }
        lockState[0] = (((Long.parseLong(lockCharacter)) % (10)) == 1);

        try {
            String pathname = ".//save.sav";
            File filename = new File(pathname);
            OutputStreamWriter writer = new OutputStreamWriter(
                    new FileOutputStream(filename));
            BufferedWriter bw = new BufferedWriter(writer);
            for (int i = 0; i < 3; i++) {
                bw.write(highScore[i] + "\n");
            }
            bw.write(lockCharacter + "\n");
            bw.write(Integer.toString(mainIcon));
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(Option.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getIcon() {
        try {
            String pathname = ".//save.sav";
            File filename = new File(pathname);
            InputStreamReader reader = new InputStreamReader(
                    new FileInputStream(filename));
            BufferedReader br = new BufferedReader(reader);
            for (int i = 0; i < 3; i++) {
                highScore[i] = br.readLine();
            }
            lockCharacter = br.readLine();
            mainIcon = Integer.parseInt(br.readLine());
            br.close();
        } catch (IOException ex) {
            Logger.getLogger(Option.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
