export const constants = {
    REGISTRATION_SUCCESS: {
        title: 'Kayıt Başarılı',
        message: 'Kayıt işleminiz başarıyla gerçekleşmiştir. Lütfen e-posta adresinizi kontrol ediniz.',
    },
    ALREADY_REGISTERED: {
        title: 'Kayıt Başarısız',
        message: 'Bu e-posta zaten kayıtlı.',
    },
    REGISTRATION_FAILED: {
        title: 'Kayıt Başarısız',
        message: 'Kayıt işleminiz gerçekleştirilemedi. Lütfen daha sonra tekrar deneyiniz.',
    },
    DAYS: ['Pazartesi', 'Salı', 'Çarşamba', 'Perşembe', 'Cuma', 'Cumartesi', 'Pazar'],
    LECTURE_HOURS: [
        { startingTime: '09:00', endingTime: '09:50' },
        { startingTime: '10:00', endingTime: '10:50' },
        { startingTime: '11:00', endingTime: '11:50' },
        { startingTime: '12:00', endingTime: '12:50' },
        { startingTime: '13:00', endingTime: '13:50' },
        { startingTime: '14:00', endingTime: '14:50' },
        { startingTime: '15:00', endingTime: '15:50' },
        { startingTime: '16:00', endingTime: '16:50' },
        { startingTime: '17:00', endingTime: '17:50' },
        { startingTime: '18:00', endingTime: '18:50' },
        { startingTime: '19:00', endingTime: '19:50' },
        { startingTime: '20:00', endingTime: '20:50' },
        { startingTime: '21:00', endingTime: '21:50' },
    ],
    ASSIGN_LECTURE_SUCCESS: {
        title: 'Ders Atama Başarılı',
        message: 'Ders atama işleminiz başarıyla gerçekleşmiştir.',
    },

    USER_TYPES: [
        { value: 'Öğrenci', endPoint: 'students' },
        { value: 'Öğretmen', endPoint: 'teachers' },
        { value: 'Veli', endPoint: 'parents' },
    ],
}
