import { Classroom } from "./Classroom";
import { Lecture } from "./Lecture";
import { User } from "./User";

export class Section{
    day?: string; 
    startingTime?: string; 
    numberOfHours?:number; 
    classroom?: Classroom;
    teacher?: User;
    lecture?: Lecture;
}