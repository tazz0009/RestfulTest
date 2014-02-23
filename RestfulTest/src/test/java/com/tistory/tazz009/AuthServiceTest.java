package com.tistory.tazz009;

import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AuthServiceTest {

	private final String USER_ID = "userId";
	private final String NO_USER_ID = "no_userId";
	private final String USER_PASSWORD = "userPassword";
	private final String WRONG_PASSWORD = "wrongPassword";

	private AuthService authService;
	private UserRepository mockUserRepository;

	@Before
	public void setUp() {
		mockUserRepository = mock(UserRepository.class);
		authService = new AuthService();
		authService.setUserRepository(mockUserRepository);
	}
	
	/*
	 * 아이디 또는 패스워드가 공백이나 null일 경우
	 * IllegalArgumentException을 발생시킨다.
	 */
	@Test
	public void givenInvalidId_throwIllegalArgEx() {
		// 아이디가 null일 경우
		assertIllegalArgExThrown(null, USER_PASSWORD);
		// 아이디가 공백일 경우
		assertIllegalArgExThrown("", USER_PASSWORD);
		// 패스워드가 null일 경우
		assertIllegalArgExThrown(USER_ID, null);
		// 패스워드가 공백일 경우
		assertIllegalArgExThrown(USER_ID, "");
		
	}

	/*
	 * IllegalArgumentException이 발생하는 것을 확인한다.
	 */
	private void assertIllegalArgExThrown(String id, String password) {
		assertExceptionThrown(id, password, IllegalArgumentException.class);
	}

	/*
	 * Exception이 발생하는 것을 확인한다.
	 */
	private void assertExceptionThrown(String id, String password,
			Class<? extends Exception> type) {
		Exception thrownEx = null;
		try {
			authService.authenticate(id, password);
		} catch (Exception e) {
			thrownEx = e;
		}
		assertThat(thrownEx , instanceOf(type));
	}

	/*
	 * user를 찾지 못했을 경우 NonExistingUserException을 발생시킨다.
	 */
	@Test
	public void whenUserNotFound_throwNotExistingUserEx() {
		assertExceptionThrown(NO_USER_ID, USER_PASSWORD, NonExistingUserException.class);
	}
	
	/*
	 * userId는 올바르나 패스워드가 잘못되었을 경우 WrongPasswordException을 발생시킨다.
	 */
	@Test
	public void wheUserFoundButWrongPw_throwWrongPasswordEx() {
		givenUserExists(USER_ID, USER_PASSWORD);
		assertExceptionThrown(USER_ID, WRONG_PASSWORD, WrongPasswordException.class);
		verifyUserFound(USER_ID);
	}

	/*
	 * User mock 객체의 findById 메소드를 호출하면 User 객체를 생성하여
	 * 리턴하도록 한다.
	 */
	private void givenUserExists(String id, String password) {
		when(mockUserRepository.findById(id)).thenReturn(new User(id, password));
		
	}

	private void verifyUserFound(String id) {
		verify(mockUserRepository).findById(id);
	}
	
	/*
	 * User의 아이디와 패스워드가 올바르게 입력되었을 경우
	 * 유저의 인증 Authentication 객체를 반환한다.
	 */
	@Test
	public void whenUserFoundAndRightPw_returnAuth() {
		givenUserExists(USER_ID, USER_PASSWORD);
		Authentication auth = authService.authenticate(USER_ID,USER_PASSWORD);
		assertThat(auth.getId(), equalTo(USER_ID));
	}
}
