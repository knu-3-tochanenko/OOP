export interface User {
  id: number;
  email: string;
  name: string;
  surname: string;
  role: string;
  password: string;
}

export function getEmptyUser(): User {
  return {
    id: null,
    email: null,
    name: null,
    surname: null,
    role: null,
    password: null
  };
}

export function getUser(id: number, email: string, name: string, surname: string, role: string, password: string): User {
  return {
    id,
    email,
    name,
    surname,
    role,
    password
  };
}
