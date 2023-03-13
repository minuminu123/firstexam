package firstexam;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	static List<Article> articles = new ArrayList<>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("프로그램 시작");
		System.out.println("테스트 데이터를 생성합니다.");
		makeTestData();
		int lastArticleId = 3;
		while (true) {
			System.out.print("명령어 > ");

			String command = sc.nextLine().trim();

			if (command.length() == 0) {
				System.out.println("명령어를 입력해주세요.");
			}
			if (command.equals("exit")) {
				break;
			}

			else if (command.equals("post list")) {
				if (articles.size() == 0) {
					System.out.println("게시글이 존재하지 않습니다.");
					continue;
				}

				System.out.println("번호   |   제목");
				for (int i = articles.size() - 1; i >= 0; i--) {
					Article article = articles.get(i);
					System.out.printf("%d   |   %s\n", article.id, article.title);
				}
			} // post list 끝

			else if (command.equals("post write")) {
				int id = lastArticleId + 1;
				System.out.print("제목: ");
				String title = sc.nextLine();
				System.out.print("내용: ");
				String body = sc.nextLine();
				String regDate = Util.getNowDate();
				String updateDate = Util.getNowDate();

				articles.add(new Article(id, regDate, updateDate, title, body));
				System.out.println(id + "번 글이 작성되었습니다.");

			} // post write 끝

			else if (command.startsWith("post detail")) {
				String[] cmdDiv = command.split(" ");

				if (cmdDiv.length < 3) {
					System.out.println("명령어를 확인해 주세요.");
					continue;
				}
				int id = Integer.parseInt(cmdDiv[2]);
				Article foundArticle = null;
				foundArticle = getArticleById(id);

				if (foundArticle == null) {
					System.out.println("게시물이 존재하지 않습니다.");
				}

				System.out.println("번호: " + foundArticle.id);
				System.out.println("생성날짜: " + foundArticle.regDate);
				System.out.println("수정날짜: " + foundArticle.updateDate);
				System.out.println("제목: " + foundArticle.title);
				System.out.println("내용: " + foundArticle.body);
				
			} // post detail 끝

			else if (command.startsWith("post delete")) {
				String[] cmdDiv = command.split(" ");

				if (cmdDiv.length < 3) {
					System.out.println("명령어를 확인해 주세요.");
					continue;
				}
				int id = Integer.parseInt(cmdDiv[2]);
				int foundIndex = getArticleIndexById(id);

				if (foundIndex == -1) {
					System.out.println("게시물이 존재하지 않습니다.");
					continue;
				}

				articles.remove(foundIndex);
				System.out.println(id + "번 게시물을 삭제했습니다.");
			} // post delete 끝

			else if (command.startsWith("post modify")) {
				String[] cmdDiv = command.split(" ");

				if (cmdDiv.length < 3) {
					System.out.println("명령어를 확인해 주세요.");
					continue;
				}
				int id = Integer.parseInt(cmdDiv[2]);

				Article foundArticle = null;
				foundArticle = getArticleById(id);

				if (foundArticle == null) {
					System.out.println("게시물이 존재하지 않습니다.");
					continue;
				}
				System.out.print("새 제목: ");
				String newTitle = sc.nextLine();
				System.out.print("새 내용: ");
				String newBody = sc.nextLine();
				String updateDate = Util.getNowDate();
				
				foundArticle.title = newTitle;
				foundArticle.body = newBody;
				foundArticle.updateDate = updateDate;
				System.out.println(id + "번 게시물을 수정했습니다.");
				
			} // post modify 끝

			else
				System.out.println("존재하지 않는 명령어 입니다.");
		}
	}

	private static Article getArticleById(int id) {
		int index = getArticleIndexById(id);
		if (index != -1) {
			return articles.get(index);
		}
		return null;
	}

	private static int getArticleIndexById(int id) {
		int i = 0;
		for (Article article : articles) {
			article = articles.get(i);
			if (article.id == id) {
				return i;
			}
			i++;
		}
		return -1;
	}

	private static void makeTestData() {
		articles.add(new Article(1, Util.getNowDate(), Util.getNowDate(), "제목1", "내용1"));
		articles.add(new Article(2, Util.getNowDate(), Util.getNowDate(), "제목2", "내용2"));
		articles.add(new Article(3, Util.getNowDate(), Util.getNowDate(), "제목3", "내용3"));

	}

}

class Article {
	int id;
	String regDate;
	String updateDate;
	String title;
	String body;

	Article(int id, String regDate, String updateDate, String title, String body) {
		this.id = id;
		this.regDate = regDate;
		this.updateDate = updateDate;
		this.title = title;
		this.body = body;
	}
}