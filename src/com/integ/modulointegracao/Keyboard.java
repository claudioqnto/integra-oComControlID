/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.integ.modulointegracao;

import java.awt.AWTException;
import java.awt.Robot;

public class Keyboard {

    private Robot robot;

    public Keyboard() throws AWTException {
        this.robot = new Robot();
    }

    /**
     *
     * @param texto - texto a ser enviado
     */
    public void type(String texto) {
        int length = texto.length();
        for (int i = 0; i < length; i++) {
            char character = texto.charAt(i);
            type(character);
        }
        doType(java.awt.event.KeyEvent.VK_ENTER);
    }

    private void type(char character) {
        switch (character) {
        case 'a': doType(java.awt.event.KeyEvent.VK_A); break;
        case 'b': doType(java.awt.event.KeyEvent.VK_B); break;
        case 'c': doType(java.awt.event.KeyEvent.VK_C); break;
        case 'd': doType(java.awt.event.KeyEvent.VK_D); break;
        case 'e': doType(java.awt.event.KeyEvent.VK_E); break;
        case 'f': doType(java.awt.event.KeyEvent.VK_F); break;
        case 'g': doType(java.awt.event.KeyEvent.VK_G); break;
        case 'h': doType(java.awt.event.KeyEvent.VK_H); break;
        case 'i': doType(java.awt.event.KeyEvent.VK_I); break;
        case 'j': doType(java.awt.event.KeyEvent.VK_J); break;
        case 'k': doType(java.awt.event.KeyEvent.VK_K); break;
        case 'l': doType(java.awt.event.KeyEvent.VK_L); break;
        case 'm': doType(java.awt.event.KeyEvent.VK_M); break;
        case 'n': doType(java.awt.event.KeyEvent.VK_N); break;
        case 'o': doType(java.awt.event.KeyEvent.VK_O); break;
        case 'p': doType(java.awt.event.KeyEvent.VK_P); break;
        case 'q': doType(java.awt.event.KeyEvent.VK_Q); break;
        case 'r': doType(java.awt.event.KeyEvent.VK_R); break;
        case 's': doType(java.awt.event.KeyEvent.VK_S); break;
        case 't': doType(java.awt.event.KeyEvent.VK_T); break;
        case 'u': doType(java.awt.event.KeyEvent.VK_U); break;
        case 'v': doType(java.awt.event.KeyEvent.VK_V); break;
        case 'w': doType(java.awt.event.KeyEvent.VK_W); break;
        case 'x': doType(java.awt.event.KeyEvent.VK_X); break;
        case 'y': doType(java.awt.event.KeyEvent.VK_Y); break;
        case 'z': doType(java.awt.event.KeyEvent.VK_Z); break;
        case 'A': doType(java.awt.event.KeyEvent.VK_SHIFT, java.awt.event.KeyEvent.VK_A); break;
        case 'B': doType(java.awt.event.KeyEvent.VK_SHIFT, java.awt.event.KeyEvent.VK_B); break;
        case 'C': doType(java.awt.event.KeyEvent.VK_SHIFT, java.awt.event.KeyEvent.VK_C); break;
        case 'D': doType(java.awt.event.KeyEvent.VK_SHIFT, java.awt.event.KeyEvent.VK_D); break;
        case 'E': doType(java.awt.event.KeyEvent.VK_SHIFT, java.awt.event.KeyEvent.VK_E); break;
        case 'F': doType(java.awt.event.KeyEvent.VK_SHIFT, java.awt.event.KeyEvent.VK_F); break;
        case 'G': doType(java.awt.event.KeyEvent.VK_SHIFT, java.awt.event.KeyEvent.VK_G); break;
        case 'H': doType(java.awt.event.KeyEvent.VK_SHIFT, java.awt.event.KeyEvent.VK_H); break;
        case 'I': doType(java.awt.event.KeyEvent.VK_SHIFT, java.awt.event.KeyEvent.VK_I); break;
        case 'J': doType(java.awt.event.KeyEvent.VK_SHIFT, java.awt.event.KeyEvent.VK_J); break;
        case 'K': doType(java.awt.event.KeyEvent.VK_SHIFT, java.awt.event.KeyEvent.VK_K); break;
        case 'L': doType(java.awt.event.KeyEvent.VK_SHIFT, java.awt.event.KeyEvent.VK_L); break;
        case 'M': doType(java.awt.event.KeyEvent.VK_SHIFT, java.awt.event.KeyEvent.VK_M); break;
        case 'N': doType(java.awt.event.KeyEvent.VK_SHIFT, java.awt.event.KeyEvent.VK_N); break;
        case 'O': doType(java.awt.event.KeyEvent.VK_SHIFT, java.awt.event.KeyEvent.VK_O); break;
        case 'P': doType(java.awt.event.KeyEvent.VK_SHIFT, java.awt.event.KeyEvent.VK_P); break;
        case 'Q': doType(java.awt.event.KeyEvent.VK_SHIFT, java.awt.event.KeyEvent.VK_Q); break;
        case 'R': doType(java.awt.event.KeyEvent.VK_SHIFT, java.awt.event.KeyEvent.VK_R); break;
        case 'S': doType(java.awt.event.KeyEvent.VK_SHIFT, java.awt.event.KeyEvent.VK_S); break;
        case 'T': doType(java.awt.event.KeyEvent.VK_SHIFT, java.awt.event.KeyEvent.VK_T); break;
        case 'U': doType(java.awt.event.KeyEvent.VK_SHIFT, java.awt.event.KeyEvent.VK_U); break;
        case 'V': doType(java.awt.event.KeyEvent.VK_SHIFT, java.awt.event.KeyEvent.VK_V); break;
        case 'W': doType(java.awt.event.KeyEvent.VK_SHIFT, java.awt.event.KeyEvent.VK_W); break;
        case 'X': doType(java.awt.event.KeyEvent.VK_SHIFT, java.awt.event.KeyEvent.VK_X); break;
        case 'Y': doType(java.awt.event.KeyEvent.VK_SHIFT, java.awt.event.KeyEvent.VK_Y); break;
        case 'Z': doType(java.awt.event.KeyEvent.VK_SHIFT, java.awt.event.KeyEvent.VK_Z); break;
        case '`': doType(java.awt.event.KeyEvent.VK_BACK_QUOTE); break;
        case '0': doType(java.awt.event.KeyEvent.VK_0); break;
        case '1': doType(java.awt.event.KeyEvent.VK_1); break;
        case '2': doType(java.awt.event.KeyEvent.VK_2); break;
        case '3': doType(java.awt.event.KeyEvent.VK_3); break;
        case '4': doType(java.awt.event.KeyEvent.VK_4); break;
        case '5': doType(java.awt.event.KeyEvent.VK_5); break;
        case '6': doType(java.awt.event.KeyEvent.VK_6); break;
        case '7': doType(java.awt.event.KeyEvent.VK_7); break;
        case '8': doType(java.awt.event.KeyEvent.VK_8); break;
        case '9': doType(java.awt.event.KeyEvent.VK_9); break;
        case '-': doType(java.awt.event.KeyEvent.VK_MINUS); break;
        case '=': doType(java.awt.event.KeyEvent.VK_EQUALS); break;
        case '~': doType(java.awt.event.KeyEvent.VK_SHIFT, java.awt.event.KeyEvent.VK_BACK_QUOTE); break;
        case '!': doType(java.awt.event.KeyEvent.VK_EXCLAMATION_MARK); break;
        case '@': doType(java.awt.event.KeyEvent.VK_AT); break;
        case '#': doType(java.awt.event.KeyEvent.VK_NUMBER_SIGN); break;
        case '$': doType(java.awt.event.KeyEvent.VK_DOLLAR); break;
        case '%': doType(java.awt.event.KeyEvent.VK_SHIFT, java.awt.event.KeyEvent.VK_5); break;
        case '^': doType(java.awt.event.KeyEvent.VK_CIRCUMFLEX); break;
        case '&': doType(java.awt.event.KeyEvent.VK_AMPERSAND); break;
        case '*': doType(java.awt.event.KeyEvent.VK_ASTERISK); break;
        case '(': doType(java.awt.event.KeyEvent.VK_LEFT_PARENTHESIS); break;
        case ')': doType(java.awt.event.KeyEvent.VK_RIGHT_PARENTHESIS); break;
        case '_': doType(java.awt.event.KeyEvent.VK_UNDERSCORE); break;
        case '+': doType(java.awt.event.KeyEvent.VK_PLUS); break;
        case '\t': doType(java.awt.event.KeyEvent.VK_TAB); break;
        case '\n': doType(java.awt.event.KeyEvent.VK_ENTER); break;
        case '[': doType(java.awt.event.KeyEvent.VK_OPEN_BRACKET); break;
        case ']': doType(java.awt.event.KeyEvent.VK_CLOSE_BRACKET); break;
        case '\\': doType(java.awt.event.KeyEvent.VK_BACK_SLASH); break;
        case '{': doType(java.awt.event.KeyEvent.VK_SHIFT, java.awt.event.KeyEvent.VK_OPEN_BRACKET); break;
        case '}': doType(java.awt.event.KeyEvent.VK_SHIFT, java.awt.event.KeyEvent.VK_CLOSE_BRACKET); break;
        case '|': doType(java.awt.event.KeyEvent.VK_SHIFT, java.awt.event.KeyEvent.VK_BACK_SLASH); break;
        case ';': doType(java.awt.event.KeyEvent.VK_SEMICOLON); break;
        case ':': doType(java.awt.event.KeyEvent.VK_COLON); break;
        case '\'': doType(java.awt.event.KeyEvent.VK_QUOTE); break;
        case '"': doType(java.awt.event.KeyEvent.VK_QUOTEDBL); break;
        case ',': doType(java.awt.event.KeyEvent.VK_COMMA); break;
        case '<': doType(java.awt.event.KeyEvent.VK_SHIFT, java.awt.event.KeyEvent.VK_COMMA); break;
        case '.': doType(java.awt.event.KeyEvent.VK_PERIOD); break;
        case '>': doType(java.awt.event.KeyEvent.VK_SHIFT, java.awt.event.KeyEvent.VK_PERIOD); break;
        case '/': doType(java.awt.event.KeyEvent.VK_SLASH); break;
        case '?': doType(java.awt.event.KeyEvent.VK_SHIFT, java.awt.event.KeyEvent.VK_SLASH); break;
        case ' ': doType(java.awt.event.KeyEvent.VK_SPACE); break;
        default:
            throw new IllegalArgumentException("Cannot type character " + character);
        }
    }

    private void doType(int... keyCodes) {
        doType(keyCodes, 0, keyCodes.length);
    }

    private void doType(int[] keyCodes, int offset, int length) {
        if (length == 0) {
            return;
        }

        robot.keyPress(keyCodes[offset]);
        doType(keyCodes, offset + 1, length - 1);
        robot.keyRelease(keyCodes[offset]);
    }
}