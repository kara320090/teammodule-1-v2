import java.util.HashMap;
import java.util.ArrayList;
import myClass.Book;
import myClass.User;
import DataBase.LibDB;
import java.util.Scanner;

/**
 * App은 LibraryManagementSystem을 실행하고 이용자와 도서 정보를 불러와 대출과 현황을 확인하는 클래스이다
 *
 * @author (2021320090 이봉헌)
 * @version (2025.10.22)
 */
public class App {
    static public void main(String[] args){
        LibraryManagementSystem LibMS = new LibraryManagementSystem();

        LibDB<User> userDB = LibMS.setUserDB("UserData2025.txt");

        System.out.println("----- 이용자 목록 출력 -----");
        LibMS.printDB(userDB);
        System.out.println();

        LibDB<Book> bookDB = LibMS.setBookDB("BookData2025.txt");

        System.out.println("----- 책 목록 출력 -----");
        LibMS.printDB(bookDB);
        System.out.println();
        
        Scanner scan = new Scanner(System.in);
        for(int i=0; i<3; i++){
            System.out.println("학생ID를 입력");
            String tempStID = scan.nextLine();
        
            System.out.println("BookID를 입력");
            String tempBookID = scan.nextLine();
            LibMS.borrowBook(tempStID, tempBookID);
        }
        
    
        // ArrayList<String> books = new ArrayList<>();
        // books.add("B01");
        // books.add("B02");
        // LibMS.borrowBook("2025320001", books);
        // LibMS.borrowBook("2024320002", "B03");
        // LibMS.borrowBook("2023320003", "B04");

        System.out.println("----- 대출 현황 -----");
        LibMS.printLoanList();
        System.out.println("--------------------");
    }
}
