import { Classroom } from "./Classroom";
import { User } from "./User";

export class Student extends User{
    address?: string;
    birthDate?: Date;
    grade?: number;
    schoolName?: string;
    hasInternet?: boolean;
    section?: string;
    classroom?: Classroom;
    parent?: User;
}