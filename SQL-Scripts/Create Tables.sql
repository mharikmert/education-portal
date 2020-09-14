CREATE TABLE ILLER(
      Il_ID bigint
    );
CREATE TABLE LOKASYON(
      Lokasyon_ID bigint,
      Lokasyon_Tanimi char(40),
      Lokasyon_Adres char(100) #adres bilgisi
      -- foreign key Lokasyon_Ili references ILLER(Il_ID);
      -- AktifPasif smallint, # aktif-> , pasif -> 0
    );
CREATE TABLE KULLANICI(
      Kullanici_ID bigint,
      -- foreign key Lokasyon_ID references LOKASYON(Lokasyon_ID);
      TC_Kimlik int(11), #Control
      Adi char(30),#büyük harf kontrolleri
      Soyadi char(30), #büyük harf kontrolleri
      Cep_Telefonu char(12), #Sadece rakam, formatlı
      E_mail char(50), #kontrollü
      -- foreign key Il_ID references ILLER(Il_ID);
      -- Ogretmen smallint,
      -- Ogrenci smallint,
      -- Veli smallint,
      -- foreign key VeliAnne references KULLANICI(Adi),
      -- foreign key VeliBaba references KULLANICI(Adi),
      Ilce_Adi char(40) #büyük harf
    );
CREATE TABLE KAYIT_LOG(
      #foreign key GirisYapan references KULLANICI(Adi),
      GirisZamani DateTime,
      #foreign key DegisiklikYapan references KULLANICI(Adi),
      DegisiklikZamani DateTime
    );
 CREATE TABLE GIRIS_LOG(
      Giris_ID bigint,
      -- foreign key GirisID references KULLANICI(Adi),
      GirisZamani DateTime
    );
CREATE TABLE PARAMETRELER(
      Parametre_ID bigint,
      Parametre_Aciklama char(50),
      Parametre_Degeri char(50),
      Parametre_Tipi char(30) #date, int bigint vb
      -- AktifPasif smallint
    );
    CREATE TABLE YETKİLER(
      Yetki_ID bigint,
      Menu_Adi char(50)
      -- Ogretmen smallint,
      -- Ogrenci smallint,
      -- Veli smallint,
      -- AktifPasif smallint,
    );
CREATE TABLE DERS_TANIMLARI(
      Ders_ID bigint,
      Ders_Adi char(30)
      -- AktifPasif smallint,
    );
CREATE TABLE DÖNEMLER(
      Donem_ID bigint,
      -- foreign key Lokasyon_ID references LOKASYON(Lokasyon_ID);
      Donem_Acıklama char(30),
      Donem_Baslangıc_Tarihi DateTime,
      Donem_Bitis_Tarihi DateTime,
      -- AktifPasif smallint
);
CREATE TABLE SINIFLAR(
      Sinif_IF bigint,
      -- foreign key Donem_ID references DONEMLER(Donem_ID),
      Sinif_Aciklama char(30),
      Kapasite int(3),
      Sinif_Yili int(2),
      -- Pazartesi smallint,
      -- Pazartesi_Bas_Saati smallint,
      -- Pazartesi_Bit_Saati smallint,
      -- Sali smallint,
      -- Sali_Bas_Saati smallint,
      -- Sali_Bit_Saati smallint,
      -- Carsamba smallint,
      -- Carsamba_Bas_Saati smallint,
      -- Carsamba_Bit_Saati smallint,
      -- Persembe smallint,
      -- Persembe_Bas_Saati smallint,
      -- Persembe_Bit_Saati smallint,
      -- Cuma smallint,
      -- Cuma_Bas_Saati smallint,
      -- Cuma_Bit_Saati smallint,
      -- Cumartesi smallint,
      -- Cumartesi_Bas_Saati smallint,
      -- Cumartesi_Bit_Saati smallint,
      -- Pazar smallint,
      -- Pazar_Bas_Saati smallint,
      -- Pazar_Bit_Saati smallint,
      -- AktifPasif smallint
    );
