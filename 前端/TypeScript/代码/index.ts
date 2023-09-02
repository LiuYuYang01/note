interface User {
  fn: () => void;
}

let user = {
  name: "lyy",
  age: 20,
  fn() {
    console.log(this.name, 111);
  },
};

function func(user: User) {
  user.fn();
}

func(user);
