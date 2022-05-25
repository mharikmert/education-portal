import { Student } from "./Student";
import { User } from "./User";

export class Parent extends User{
    id?: bigint; 
    students?: Student[];
}