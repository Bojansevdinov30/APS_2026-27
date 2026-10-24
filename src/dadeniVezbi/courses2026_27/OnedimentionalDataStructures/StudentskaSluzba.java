package dadeniVezbi.courses2026_27.OnedimentionalDataStructures;

import dataStructures.LinkedQueue;

import java.util.Scanner;
// vakvi dve zadaci bea, ovaa e vtorata, prvata e ista samo razlicen redosled
/*Студентска служба е посетена од студентите со цел да приложат/земат документи. Студентот може да приложи документи, да побара да си го земе индексот или пак да побара да си ги земе документите од средно. Кога студентската служба ќе започне со работа се услужуваат студенти од сите три типа паралелно, но исто така сите три шалтера не одат со иста брзина па услужувањето е со следниот алгоритам (два студента што ги приложуваат документите, па секвенцијално три студенти што сакаат да си го земат индексот, па секвенцијално еден студент што сака да си ги земе документите од средно).

Доколку студент чека ред за повеќе услуги кај студентската служба, тој чека ред првин во редицата за приложување на документи, потоа во редицата за земање на индекс и на крај во редицата за земање на документи од средно.

Влез: Во првата линија е даден број на студенти кои имаат дојдено за да бидат услужени од студентската служба. Потоа 4 редици се внесуваат за секој студент, каде првата линија е име на студент, а во останатите 3 редици се внесува дали има потреба од дадена услуга (приложување документи, земање на индекс, земање на документи од средно соодветно), каде 1 значи дека има потреба од услуга од тој тип, 0 значи дека нема потреба од услуга од тој тип.

Пример:

Иван Ивановски

1

1

0

значи дека студентот Иван Ивановски има за цел да приложи документи и да си го земе индексот.

Излез: Испечати го редоследот на студентите по редослед како завршуваат со сите услуги од студенстката служба.*/
public class StudentskaSluzba {
    public static class Student {
        String name;
        int priloziDokumenti;
        int zemiIndeks;
        int pobarajDokumenti;

        public Student(String name, int priloziDokumenti, int zemiIndeks, int pobarajDokumenti) {
            this.name = name;
            this.priloziDokumenti = priloziDokumenti;
            this.zemiIndeks = zemiIndeks;
            this.pobarajDokumenti = pobarajDokumenti;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPriloziDokumenti() {
            return priloziDokumenti;
        }

        public void setPriloziDokumenti(int priloziDokumenti) {
            this.priloziDokumenti = priloziDokumenti;
        }

        public int getZemiIndeks() {
            return zemiIndeks;
        }

        public void setZemiIndeks(int zemiIndeks) {
            this.zemiIndeks = zemiIndeks;
        }

        public int getPobarajDokumenti() {
            return pobarajDokumenti;
        }

        public void setPobarajDokumenti(int pobarajDokumenti) {
            this.pobarajDokumenti = pobarajDokumenti;
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
        LinkedQueue<Student> prilozi = new LinkedQueue<>();
        LinkedQueue<Student> indeks = new LinkedQueue<>();
        LinkedQueue<Student> pobaraj = new LinkedQueue<>();
        for (int i = 0; i < n; i++) {
            Student s = new Student(input.nextLine(), input.nextInt(), input.nextInt(), input.nextInt()); // tuka mozes i so Integer.parseInt(input.nextLine()) pa bez toa edna linija podole sto e
            input.nextLine();
            if (s.getPriloziDokumenti() == 1) {
                prilozi.enqueue(s);
            } else if (s.getZemiIndeks() == 1) {
                indeks.enqueue(s);
            } else if (s.getPobarajDokumenti() == 1) {
                pobaraj.enqueue(s);
            }
        }

        while (!prilozi.isEmpty() || !indeks.isEmpty() || !pobaraj.isEmpty()) {
            for (int i = 0; i < 2; i++) {
                if (!prilozi.isEmpty()) {
                    Student s = prilozi.dequeue();
                    s.setPriloziDokumenti(s.getPriloziDokumenti() - 1);
                    if (s.getZemiIndeks() == 1) {
                        indeks.enqueue(s);
                    } else if (s.getPobarajDokumenti() == 1) {
                        pobaraj.enqueue(s);
                    } else {
                        System.out.println(s.toString());
                    }
                }
            }
            for (int i = 0; i < 3; i++) {
                if (!indeks.isEmpty()) {
                    Student s = indeks.dequeue();
                    s.setZemiIndeks(s.getZemiIndeks() - 1);
                    if (s.getPobarajDokumenti() == 1) {
                        pobaraj.enqueue(s);
                    } else {
                        System.out.println(s.toString());
                    }
                }
            }

            if (!pobaraj.isEmpty()) {
                Student s = pobaraj.dequeue();
                s.setPobarajDokumenti(s.getPobarajDokumenti() - 1);
                System.out.println(s.toString());
            }


        }

    }

}
