/*
 В маленьком интернет кафе в деревне есть N компьютеров. Кафе работает по принципу FIFO,
 т.е. когда все компьютеры заняты, то очередному желающему необходимо подождать.
 В кафе приходит M туристов. Турист проводит за компьютеров от 15 минут до 2 часов (время выбирается случайно).
 Напишите программу, которая имитирует порядок доступа к компьютерам в кафе и выводит жунал доступа на экран
 в процессе работы. Необходимо так выбрать масштаб для времени в имитации, чтобы время выполнения программы было
 меньше 3 минут.


 M и N вводятся с клавиатуры.
 Можно использовать thread sleep 4000.

 Пример вывода:

 Tourist 1 is online. -- получил доступ к компьютеру
 Tourist 2 is done, having spent 15 minutes online. -- провёл 15 минут онлайн и освободил компьютер.
 The place is empty, let's close up and go to the beach! -- кофе опустело.
 */
// TODO: lab9

package org.suai;

import org.suai.logaccess.LogAccess;
import java.util.Scanner;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        Logger log = Logger.getLogger(Main.class.getName());
        Scanner in = new Scanner(System.in);
        log.info("Inpout count computers:");
        int n = in.nextInt();
        in.nextLine();
        log.info("Inpout count tourists:");
        int m = in.nextInt();
        in.nextLine();
        new LogAccess(n, m);
    }
}
