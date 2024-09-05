package days04;

public class Ex02 {

	public static void main(String[] args) {

		int totalRecord = 136;
		int currentPage = 1;
		int numberPerPage = 10; //한페이지에 뿌릴 게시글 수 
		
		int totalPages = (int) Math.ceil ( (double)totalRecord / numberPerPage ); //14
		System.out.println( totalPages);
		
		int start, end;

		
		for (int i = 1; i <=  totalPages; i++) {
			start = (i-1) * numberPerPage + 1;
			end = start + numberPerPage -1;
			if (end> totalRecord) {
				end = totalRecord;
			}
			
			System.out.printf("%d 페이지 : start=%d~end=%d\n", i , start, end);
		}

	}//main

}//class
