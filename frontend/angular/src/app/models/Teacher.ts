import { Lecture } from "./Lecture";
import { User } from "./User";

export class Teacher extends User{
    id?: bigint;
    lecture?: Lecture;
}