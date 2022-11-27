import { Classroom } from "./Classroom";
import { Role } from "./Role";

export class User {
    id?: bigint;
    firstName?: string;
    lastName?: string;
    email?: string;
    phoneNumber?: string;
    type?: string;
    section?: string;
    classroom?: Classroom;
    city?: string;
    address?: string;
    password?: string;
    createdAt?: Date;
    district?: string;
    approved?: boolean;
    birthDate?: Date;
    grade?: number;
    schoolName?: string;
    parent?: User;
    hasInternet?: boolean;
    roles?: Role[];
}

export interface UserType {
    value: string;
    endPoint: string;
}