package com.tistory.tazz009;


public class AuthService {

	private UserRepository userRepository;

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	/**
	 * �Էµ� id�� password�� User ������ �Ѵ�.
	 * @param id
	 * @param password
	 * @return
	 */
	public Authentication authenticate(String id, String password) {
		assertIdAndPw(id, password);
		User user = findUserOrThrowEx(id);
		throwExIfPasswordWrong(password, user);
		return createAuthentication(user);
	}

	/**
	 * �Էµ� id�� password�� ���� �Ǵ� null�� �ƴ����� Ȯ���Ѵ�.
	 * @param id
	 * @param password
	 */
	private void assertIdAndPw(String id, String password) {
		if (id == null || id.isEmpty()) {
			throw new IllegalArgumentException();
		}
		if (password == null || password.isEmpty()) {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * <pre>
	 * �Էµ� id�� User ��ü�� �����Ͽ� �����Ѵ�.
	 * id�� �������� ������ NonExistingUserException�� �߻���Ų��.
	 * </pre>
	 * @param id
	 * @return
	 */
	private User findUserOrThrowEx(String id) {
		User user = findUserById(id);
		if (user == null) {
			throw new NonExistingUserException();
		}
		return user;
	}

	/**
	 * <pre>
	 * �Էµ� �н����尡 ��ġ���� ���� ���
	 * WrongPasswordException�� �߻���Ų��.
	 * </pre>
	 * @param password
	 * @param user
	 */
	private void throwExIfPasswordWrong(String password, User user) {
		if (!user.matchPassword(password)) {
			throw new WrongPasswordException();
		}
	}

	/**
	 * �Է¹��� User ��ü�� Authentication ��ü�� �����Ѵ�.
	 * @param user
	 * @return
	 */
	private Authentication createAuthentication(User user) {
		return new Authentication(user.getId());
	}

	/**
	 * �Է¹��� id�� User ��ü�� �����Ͽ� �����Ѵ�.
	 * @param id
	 * @return
	 */
	private User findUserById(String id) {
		return userRepository.findById(id);
	}

}