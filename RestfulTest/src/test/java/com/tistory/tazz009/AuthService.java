package com.tistory.tazz009;


public class AuthService {

	private UserRepository userRepository;

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	/**
	 * 입력된 id와 password로 User 인증을 한다.
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
	 * 입력된 id와 password가 공백 또는 null이 아닌지를 확인한다.
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
	 * 입력된 id로 User 객체를 생성하여 리턴한다.
	 * id가 존재하지 않으면 NonExistingUserException을 발생시킨다.
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
	 * 입력된 패스워드가 일치하지 않을 경우
	 * WrongPasswordException을 발생시킨다.
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
	 * 입력받은 User 객체의 Authentication 객체를 생성한다.
	 * @param user
	 * @return
	 */
	private Authentication createAuthentication(User user) {
		return new Authentication(user.getId());
	}

	/**
	 * 입력받은 id로 User 객체를 생성하여 리턴한다.
	 * @param id
	 * @return
	 */
	private User findUserById(String id) {
		return userRepository.findById(id);
	}

}