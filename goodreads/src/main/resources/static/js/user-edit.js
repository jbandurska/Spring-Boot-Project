const editUserForm = document.getElementById("edit-user-form");
const usernameInput = document.getElementById("username-input");
const passwordInput = document.getElementById("password-input");

const onSubmit = async (e) => {
  e.preventDefault();

  try {
    await axios.patch("/user", {
      username: usernameInput.value,
      password: passwordInput.value,
    });

    window.location.replace("/home");
  } catch (err) {
    console.error(err);
  }
};

editUserForm.addEventListener("submit", onSubmit);

const deleteUser = async () => {
  try {
    await axios.delete("/user/1");
  } catch (err) {
    console.error(err);
  }
};
