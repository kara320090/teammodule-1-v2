import java.util.Scanner;
import java.util.ArrayList;
import myClass.Book;
import myClass.User;
import DataBase.LibDB;

/**
 * App은 LibraryManagementSystem을 실행하고 이용자와 도서 정보를 불러와
 * 쉼표(,) 구분 입력을 통해 대출을 처리하는클래스이다.
 *
 * @author (2021320090 이봉헌)
 * @version (2025.10.22)
 */
public class App {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        LibraryManagementSystem LibMS = new LibraryManagementSystem();

        LibDB<User> userDB = LibMS.setUserDB("UserData2025.txt");
        LibDB<Book> bookDB = LibMS.setBookDB("BookData2025.txt");

        while (true) {
            System.out.println("===== 이용자 목록 =====");
            LibMS.printDB(userDB);
            System.out.println();
            System.out.println("===== 도서 목록 =====");
            LibMS.printDB(bookDB);
            System.out.println();
            System.out.print("\n대출을 진행하시겠습니까? (Y/N): ");
            String cont = input.nextLine().trim().toUpperCase();

            if (cont.equals("N")) {
                break; // 프로그램 종료
            } else if (cont.equals("Y") == false) {
                System.out.println("잘못된 입력입니다. Y 또는 N만 입력하세요.");
                continue; // 다시 질문으로 돌아감
            }

            System.out.print("이용자 ID 입력: ");
            String userID = input.nextLine().trim();

            User userCheck = LibMS.userDB.findElement(userID);
            if (userCheck == null) {
                System.out.println("이용자 ID가 존재하지 않습니다: " + userID);
                continue;
            }

            System.out.println("대출할 책 ID(복수권은 쉼표로 구분): 예) B01 또는 B01,B02,B03");
            System.out.print("입력 → ");
            String line = input.nextLine().trim();

            String[] tokens = line.split(",");
            ArrayList<String> books = new ArrayList<String>();
            for (String t : tokens) {
                if (!t.trim().isEmpty()) {
                    books.add(t.trim());
                }
            }

            if (books.size() > 0) {
                LibMS.borrowBook(userID, books);
            } else {
                System.out.println("입력된 책 ID가 없습니다.");
            }

            System.out.println("\n현재 대출 현황:");
            LibMS.printLoanList();
        }

        System.out.println("\n===== 프로그램 종료 =====");
        input.close();
    }
}
