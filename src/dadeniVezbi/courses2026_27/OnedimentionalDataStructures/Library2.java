package dadeniVezbi.courses2026_27.OnedimentionalDataStructures;

import dataStructures.LinkedQueue;

import java.util.Scanner;

/*Библиотека е посетена од студентите со цел да изнајмат еден или повеќе типови на книги. Дадена книга може да припаѓа на една од трите категории: Наука, Научна фантастика или Историја. Кога библиотеката ќе започне со работа се услужуваат студенти од сите три типа паралелно, но исто така сите три шалтера не одат со иста брзина па услужувањето е со следниот алгоритам (два студента што бараат книга од тип Наука, па секвенцијално еден студент што бара книга од тип Научна фантастика, па секвенцијално два студенат што бараат книга од тип Историја).

Доколку студент чека ред за книги од различен тип, тој чека ред првин во редицата за книга од тип Наука, потоа во редицата за книга од тип Научна фантастика и на крај во редицата за книга од тип Историја (во зависност ако ги бара овие книги за позајмување).

Влез: Во првата линија е даден број на студенти кои имаат дојдено во библиотеката да позајмат книга. Потоа 4 редици се внесуваат за секој студент, каде првата линија е име на студент, а во останатите 3 редици се внесува дали студентот ќе позајми книга од даден тип (Наука, Научна фантастика и Историја соодветно), каде 1 значи дека има за цел да позајми книга од тој тип, 0 значи дека нема да позајми книга од тој тип.

Пример:

Иван Ивановски

1

1

0

значи дека студентот Иван Ивановски има за цел да позајми книга од тип Наука и тип Научна фантастика, но не има за цел да позајми книга од тип Историја.

Излез: Испечати го редоследот на студентите по редослед како завршуваат со позајмување на сите книги.*/
public class Library2 {
    public static class Student {
        String name;
        int scienceBook;
        int scienceFictionBook;
        int historyBook;

        public Student(String name, int scienceBook, int scienceFictionBook, int historyBook) {
            this.name = name;
            this.scienceBook = scienceBook;
            this.scienceFictionBook = scienceFictionBook;
            this.historyBook = historyBook;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getScienceBook() {
            return scienceBook;
        }

        public void setScienceBook(int scienceBook) {
            this.scienceBook = scienceBook;
        }

        public int getScienceFictionBook() {
            return scienceFictionBook;
        }

        public void setScienceFictionBook(int scienceFictionBook) {
            this.scienceFictionBook = scienceFictionBook;
        }

        public int getHistoryBook() {
            return historyBook;
        }

        public void setHistoryBook(int historyBook) {
            this.historyBook = historyBook;
        }

        @Override
        public String toString() {
            return this.name;
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        input.nextLine();
        LinkedQueue<Student> science = new LinkedQueue<>();
        LinkedQueue<Student> scienceFiction = new LinkedQueue<>();
        LinkedQueue<Student> history = new LinkedQueue<>();
        for (int i = 0; i < n; i++) {
            Student s = new Student(input.nextLine(), input.nextInt(), input.nextInt(), input.nextInt()); // tuka mozes i so Integer.parseInt(input.nextLine()) pa bez toa edna linija podole sto e
            input.nextLine();
            if (s.getScienceBook() == 1) {
                science.enqueue(s);
            } else if (s.getScienceFictionBook() == 1) {
                scienceFiction.enqueue(s);
            } else if (s.getHistoryBook() == 1) {
                history.enqueue(s);
            }
        }

        while (!science.isEmpty() || !scienceFiction.isEmpty() || !history.isEmpty()) {
            for (int i = 0; i < 2; i++) {
                if (!science.isEmpty()) {
                    Student s = science.dequeue();
                    s.setScienceBook(s.getScienceBook() - 1);
                    if (s.getScienceFictionBook() == 1) {
                        scienceFiction.enqueue(s);
                    } else if (s.getHistoryBook() == 1) {
                        history.enqueue(s);
                    } else {
                        System.out.println(s.toString());
                    }
                }
            }
            if (!scienceFiction.isEmpty()) {
                Student s = scienceFiction.dequeue();
                s.setScienceFictionBook(s.getScienceFictionBook() - 1);
                if (s.getHistoryBook() == 1) {
                    history.enqueue(s);
                } else {
                    System.out.println(s.toString());
                }
            }
            for (int i = 0; i < 2; i++) {
                if (!history.isEmpty()) {
                    Student s = history.dequeue();
                    s.setHistoryBook(s.getHistoryBook() - 1);
                    System.out.println(s.toString());
                }
            }

        }

    }

}