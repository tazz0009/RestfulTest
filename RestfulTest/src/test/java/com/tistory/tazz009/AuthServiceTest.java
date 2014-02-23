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
	 * ���̵� �Ǵ� �н����尡 �����̳� null�� ���
	 * IllegalArgumentException�� �߻���Ų��.
	 */
	@Test
	public void givenInvalidId_throwIllegalArgEx() {
		// ���̵� null�� ���
		assertIllegalArgExThrown(null, USER_PASSWORD);
		// ���̵� ������ ���
		assertIllegalArgExThrown("", USER_PASSWORD);
		// �н����尡 null�� ���
		assertIllegalArgExThrown(USER_ID, null);
		// �н����尡 ������ ���
		assertIllegalArgExThrown(USER_ID, "");
		
	}

	/*
	 * IllegalArgumentException�� �߻��ϴ� ���� Ȯ���Ѵ�.
	 */
	private void assertIllegalArgExThrown(String id, String password) {
		assertExceptionThrown(id, password, IllegalArgumentException.class);
	}

	/*
	 * Exception�� �߻��ϴ� ���� Ȯ���Ѵ�.
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
	 * user�� ã�� ������ ��� NonExistingUserException�� �߻���Ų��.
	 */
	@Test
	public void whenUserNotFound_throwNotExistingUserEx() {
		assertExceptionThrown(NO_USER_ID, USER_PASSWORD, NonExistingUserException.class);
	}
	
	/*
	 * userId�� �ùٸ��� �н����尡 �߸��Ǿ��� ��� WrongPasswordException�� �߻���Ų��.
	 */
	@Test
	public void wheUserFoundButWrongPw_throwWrongPasswordEx() {
		givenUserExists(USER_ID, USER_PASSWORD);
		assertExceptionThrown(USER_ID, WRONG_PASSWORD, WrongPasswordException.class);
		verifyUserFound(USER_ID);
	}

	/*
	 * User mock ��ü�� findById �޼ҵ带 ȣ���ϸ� User ��ü�� �����Ͽ�
	 * �����ϵ��� �Ѵ�.
	 */
	private void givenUserExists(String id, String password) {
		when(mockUserRepository.findById(id)).thenReturn(new User(id, password));
		
	}

	private void verifyUserFound(String id) {
		verify(mockUserRepository).findById(id);
	}
	
	/*
	 * User�� ���̵�� �н����尡 �ùٸ��� �ԷµǾ��� ���
	 * ������ ���� Authentication ��ü�� ��ȯ�Ѵ�.
	 */
	@Test
	public void whenUserFoundAndRightPw_returnAuth() {
		givenUserExists(USER_ID, USER_PASSWORD);
		Authentication auth = authService.authenticate(USER_ID,USER_PASSWORD);
		assertThat(auth.getId(), equalTo(USER_ID));
	}
}
