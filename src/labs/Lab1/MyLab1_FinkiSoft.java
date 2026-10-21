package labs.Lab1;

import java.util.Scanner;

/*Write a program that calculates salaries in the software company FINKI Soft.

The class Employee is the base class. Each employee has:

id (String)
name (String)
baseSalary (double)
The method calculateSalary() returns the base salary. The method toString() returns a string in the format ID NAME salary=SALARY, where SALARY is the result of calculateSalary(), printed with 2 decimals.

The class Developer extends Employee and adds the attribute overtimeHours (int). The salary is the base salary plus 300 per overtime hour. toString() appends overtime=H.

The class TeamLead extends Developer and adds the attribute teamSize (int). The salary is the Developer salary plus 1000 per team member. If the team has 5 or more members, the total salary is increased by 5%. toString() appends team=T.

The class Intern extends Employee and adds the attribute months (int). An intern receives 60% of the base salary, or 80% if they have been with the company 6 or more months. toString() appends months=M.

The class Company stores employees in an Employee[] array. It is created with a name and a capacity. It must have the following methods:

hire(Employee e) – adds an employee
printAll() – prints === NAME ===, then each employee on a separate line
printStatistics() – prints Developers: X, Team leads: Y, Interns: Z, Others: W. Team leads are not counted as developers, and Others are plain employees.
totalPayroll() – the sum of all salaries
averageSalary() – the average salary
highestPaid() – the employee with the highest salary; on a tie, return the first one
countAbove(double threshold) – the number of employees whose salary is strictly greater than the threshold
All subclass constructors must call super(...). All subclass calculations must use super.calculateSalary(). Generics are not allowed (ArrayList<>, List<>, etc.).

Input:

the first line contains N, the number of employees
then follow N lines, each in one of these formats:
E id name base
D id name base overtime
L id name base overtime teamSize
I id name base months
the last line contains the threshold X
Output:
Create a company named FINKI Soft and print the following, in this order:

the list of employees (printAll)
the statistics (printStatistics)
Total payroll: TOTAL
Average salary: AVG
Highest paid: ID NAME (SALARY)
Employees with salary above X: COUNT
All decimal values are printed with 2 decimals.*/
public class MyLab1_FinkiSoft {
    static class Employee {
        protected String id;
        protected String name;
        protected double baseSalary;

        public Employee(String id, String name, double baseSalary) {
            this.id = id;
            this.name = name;
            this.baseSalary = baseSalary;
        }

        public double calculateSalary() {
            return baseSalary;
        }

        @Override
        public String toString() {
            return String.format("%s %s salary=%.2f", id, name, calculateSalary());
        }
    }

    static class Developer extends Employee {
        protected int overtimeHours;

        public Developer(String id, String name, double baseSalary, int overtimeHours) {
            super(id, name, baseSalary);
            this.overtimeHours = overtimeHours;
        }

        @Override
        public double calculateSalary() {
            return super.calculateSalary() + 300 * overtimeHours;
        }

        @Override
        public String toString() {
            return String.format("%s %s salary=%.2f overtime=%d", id, name, calculateSalary(), overtimeHours);
        }
    }

    static class TeamLead extends Developer {
        protected int teamSize;

        public TeamLead(String id, String name, double baseSalary, int overtimeHours, int teamSize) {
            super(id, name, baseSalary, overtimeHours);
            this.teamSize = teamSize;
        }

        @Override
        public double calculateSalary() {
            double salary = super.calculateSalary() + 1000 * teamSize;
            if (teamSize >= 5) {
                return 1.05 * salary;
            }
            return salary;
        }

        @Override
        public String toString() {
            return String.format("%s %s salary=%.2f overtime=%d team=%d", id, name, calculateSalary(), overtimeHours, teamSize);
        }

    }

    static class Intern extends Employee {
        protected int months;

        public Intern(String id, String name, double baseSalary, int months) {
            super(id, name, baseSalary);
            this.months = months;
        }

        @Override
        public double calculateSalary() {
            return (this.months >= 6 ? super.calculateSalary() * 0.8 : super.calculateSalary() * 0.6);
        }

        @Override
        public String toString() {
            return String.format("%s %s salary=%.2f months=%d", id, name, calculateSalary(), months);
        }
    }

    static class Company {
        private Employee[] employees;
        private String name;
        private int capacity;
        private int nrEmployees;

        public Company(String name, int capacity) {
            this.name = name;
            this.capacity = capacity;
            this.employees = new Employee[capacity];
            this.nrEmployees = 0;
        }

        public void hire(Employee e) {
            employees[nrEmployees++] = e;
        }

        public void printAll() {
            System.out.println("=== " + this.name + " ===");
            for (int i = 0; i < nrEmployees; i++) {
                System.out.println(employees[i].toString());
            }
        }

        public void printStatistics() {
            int devs = 0;
            int leads = 0;
            int interns = 0;
            int others = 0;
            for (int i = 0; i < nrEmployees; i++) {
                if (employees[i] instanceof TeamLead) {
                    leads++;
                } else if (employees[i] instanceof Developer) {
                    devs++;
                } else if (employees[i] instanceof Intern) {
                    interns++;
                } else {
                    others++;
                }
            }
            System.out.println("Developers: " + devs + ", Team leads: " + leads + ", Interns: " + interns + ", Others: " + others);
        }

        public double totalPayroll() {
            double total = 0;
            for (int i = 0; i < nrEmployees; i++) {
                total += employees[i].calculateSalary();
            }
            return total;
        }

        public double averageSalary() {
            return (totalPayroll() / nrEmployees);
        }

        public Employee highestPaid() {
            Employee highest = employees[0];
            for (int i = 1; i < nrEmployees; i++) {
                if (employees[i].calculateSalary() > highest.calculateSalary()) {
                    highest = employees[i];
                }
            }
            return highest;
        }

        public int countAbove(double threshold) {
            int count = 0;
            for (int i = 0; i < nrEmployees; i++) {
                if (employees[i].calculateSalary() > threshold) {
                    count++;
                }
            }
            return count;
        }

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String type;
        Company finki = new Company("FINKI Soft", n);
        for (int i = 0; i < n; i++) {
            type = sc.next();
            if (type.equals("E")) {
                String id = sc.next();
                String name = sc.next();
                double baseSalary = sc.nextDouble();
                Employee temp = new Employee(id, name, baseSalary);
                finki.hire(temp);
            } else if (type.equals("D")) {
                String id = sc.next();
                String name = sc.next();
                double baseSalary = sc.nextDouble();
                int overtimeHours = sc.nextInt();
                Developer temp = new Developer(id, name, baseSalary, overtimeHours);
                finki.hire(temp);
            } else if(type.equals("L")) {
                String id = sc.next();
                String name = sc.next();
                double baseSalary = sc.nextDouble();
                int overtimeHours = sc.nextInt();
                int teamSize = sc.nextInt();
                TeamLead temp = new TeamLead(id, name, baseSalary, overtimeHours, teamSize);
                finki.hire(temp);
            }else{
                String id = sc.next();
                String name = sc.next();
                double baseSalary = sc.nextDouble();
                int months = sc.nextInt();
                Intern temp = new Intern(id, name, baseSalary, months);
                finki.hire(temp);
            }
        }
        double threshhold = sc.nextDouble();

        finki.printAll();

        finki.printStatistics();

        String payroll = String.format("Total payroll: %.2f", finki.totalPayroll());
        System.out.println(payroll);

        String avgSalary = String.format("Average salary: %.2f", finki.averageSalary());
        System.out.println(avgSalary);

        Employee highest = finki.highestPaid();
        System.out.printf("Highest paid: %s %s (%.2f)%n", highest.id, highest.name, highest.calculateSalary());

        System.out.printf("Employees with salary above %.2f: %d", threshhold, finki.countAbove(threshhold));

    }
}
