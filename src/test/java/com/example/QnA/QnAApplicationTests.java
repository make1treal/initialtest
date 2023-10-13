package com.example.QnA;

import com.example.QnA.domain.entity.Answer;
import com.example.QnA.domain.entity.Question;
import com.example.QnA.repository.AnswerRepository;
import com.example.QnA.repository.QuestionRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.beans.Transient;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class QnAApplicationTests {

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private AnswerRepository answerRepository;

//	@Transactional //이거 달면 메서드 끝날 때까지 DB세션 꺼지지 않음
	@Test
	void testJpa() {

		/////////// 1. Question엔티티 테스트

//		 아래는 Question 엔티티 객체 2개를 DB에 저장하는 코드
//		첫번째는 데이터 불변 위해 빌더패턴 이용
		Question q1 = Question.builder()
//				.id(1) // id 권한을 DB에 위임했으니까 -> 굳이 내가 안 넣어도 됨
				.subject("sbb가 무엇인가요?")
				.content("sbb에 대해 알고 싶습니다.")
				.createDate(LocalDateTime.now())
				.build();
		this.questionRepository.save(q1);  // 첫번째 질문 저장


		// 이번엔 Setter활용(이건 나중에 데이터 수정해보려고 일부러 setter씀)
		Question q2 = new Question();
		q2.setSubject("스프링부트 모델 질문입니다.");
		q2.setContent("id는 자동으로 생성되나요?");
		q2.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q2);  // 두번째 질문 저장


		// 저장 후 DB에서 데이터 조회하는 코드
		List<Question> all = this.questionRepository.findAll();
		assertEquals(2, all.size());//위에서 엔티티객체 2개 넣었으니까 총 2개 맞냐 확인

		Question q = all.get(0); // 인덱스 0을 get으로 꺼내오기
		assertEquals("sbb가 무엇인가요?", q.getSubject());


		Optional<Question> oq = this.questionRepository.findById(1);
		//findById의 리턴값은 항상 Optional임!
		if (oq.isPresent()) {
			Question qq = oq.get();//Optional이니까 get으로 한 번 까서 Question얻기
			assertEquals("sbb가 무엇인가요?", q.getSubject());
		}

		Question qqq = this.questionRepository.findBySubject("sbb가 무엇인가요?");
		assertEquals(1, qqq.getId());

		Question forsubandcont = this.questionRepository.findBySubjectAndContent(
				"sbb가 무엇인가요?", "sbb에 대해 알고 싶습니다.");
		assertEquals(1, forsubandcont.getId());


		//제목에 특정 문자열이 포함되어있는 데이터 조회하기
		List<Question> qList = this.questionRepository.findBySubjectLike("sbb%");
		Question newq = qList.get(0);
		assertEquals(1, newq.getId());


		//데이터 수정하기
		Optional<Question> oqq = this.questionRepository.findById(2);
		assertTrue(oqq.isPresent());
		Question ques = oqq.get(); //Optional을 get으로 한 번 까서 Question객체 꺼내기
		ques.setSubject("수정된 제목");
		this.questionRepository.save(ques);


		//데이터 삭제하기
//		assertEquals(2, this.questionRepository.count());
//		Optional<Question> fordel = this.questionRepository.findById(1);
//		assertTrue(fordel.isPresent());
//		Question realfordel = fordel.get();
//		this.questionRepository.delete(realfordel);
//		assertEquals(1, this.questionRepository.count());




		////// 2. Answer 엔티티 테스트1

		 //데이터 생성 및 저장
//		Optional<Question> test1 = this.questionRepository.findById(2);
//		assertTrue(test1.isPresent());
//		Question test11 = test1.get();
//
//		Answer a = new Answer();
//		a.setContent("네 자동으로 생성됩니다.");
//		a.setQuestion(test11); //어떤 질문의 답변인지 알기 위해 Question객체가 필요함
//		//미리 Answer엔티티 안에 만들어놓은 Question메서드에 test11이라는 Question 세팅하기
//		a.setCreatedDate(LocalDateTime.now());
//		this.answerRepository.save(a);
////
////		// 답변 조회하기
//		Optional<Answer> test2 = this.answerRepository.findById(1);
//		assertTrue(test2.isPresent());
//		Answer test22 = test2.get();
//		//getQuestion()으로 이 답변에 연결된 질문 찾아서 -> id 대조
//		// (Answer엔티티 안에 Question 만들어놔서 가능한 것)
//		assertEquals(2, test22.getQuestion().getId());
//		//윗 블록에서 a안에 세팅한 Question test11의 id가 2였으니까ㅇㅇ 맞냐?고 확인
////
//////		세연아 천천히해 내가 따라잡기 힘들자나 너 하루에 8시간씩하면 내가 어케 따라잡니이...
//////		고연전 화이팅^^!!!!!
////
////		//답변 조회하기(질문에 연결된 답변(들) 찾기)
//		Optional<Question> test3 = this.questionRepository.findById(2);
//		assertTrue(test3.isPresent());
//		Question test33 = test3.get();
//
//		List<Answer> answerList = test33.getAnswerList();
//			//Question엔티티 안에 answerList 만들어놨기 때문에 가능
//
//		assertEquals(1, answerList.size());
//		assertEquals("네 자동으로 생성됩니다.", answerList.get(0).getContent());
	}
}

