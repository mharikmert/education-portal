export class Lecture {
    id?: bigint;
    name?: string;
    lectureCode?: string;
}

export interface LectureHour {
    startingTime: string;
    endingTime: string;
}