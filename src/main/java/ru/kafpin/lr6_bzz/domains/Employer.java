package ru.kafpin.lr6_bzz.domains;
/**
 * Класс Сотрудник со свойствами <b>clientID</b>, <b>surName</b>, <b>name</b>, <b>patronym</b>, <b>phone</b>, <b>city</b>, <b>street</b>, <b>house</b> и <b>appartment</b>.
 * <p>
 *     Данный класс позволяет описать экземпляр сотрудника с заданными фамилией,
 *     именем, отчеством, номером телефона, городом, улицей, номером дома и номером квартиры.
 * </p>
 * @author Ярослав Кокурин
 * @version 1.0
 */
public class Employer extends Man{
    /** Поле идентификатор*/
    private long employerID;
    /** Поле город*/
    private String city;
    /** Поле улица*/
    private String street;
    /** Поле дом*/
    private int house;
    /** Поле квартира*/
    private Integer appartment;
    /**
     * Функция получения информации о сотруднике
     * @return возвращает строку с информацией о сотруднике
     */
    @Override
    public String toString() {
        return getSurName()+' '+getName()+' '+getPatronym();
    }
    /**
     * Конструктор – создание нового экземпляра с заданными параметрами
     * @param employerID идентификатор сотрудника
     * @param surName фамилия сотрудника
     * @param name имя сотрудника
     * @param patronym отчество сотрудника
     * @param phone номер телефона сотрудника
     * @param city город сотрудника
     * @param street улица сотрудника
     * @param house номер дома сотрудника
     * @see Employer#Employer(long, String, String, String, String, String, String, int)
     */
    public Employer(long employerID, String surName, String name, String patronym, String phone,  String city, String street, int house) {
        super(surName, name, patronym, phone);
        this.employerID = employerID;
        this.city = city;
        this.street = street;
        this.house = house;
    }
    /**
     * Конструктор – создание нового экземпляра с заданными параметрами
     * @param surName фамилия сотрудника
     * @param name имя сотрудника
     * @param patronym отчество сотрудника
     * @param phone номер телефона сотрудника
     * @param city город сотрудника
     * @param street улица сотрудника
     * @param house номер дома сотрудника
     * @see Employer#Employer(String, String, String, String, String, String, int)
     */
    public Employer(String surName, String name, String patronym, String phone, String city, String street, int house) {
        super(surName, name, patronym, phone);
        this.city = city;
        this.street = street;
        this.house = house;
    }
    /**
     * Конструктор – создание нового экземпляра с заданными параметрами
     * @param surName фамилия сотрудника
     * @param name имя сотрудника
     * @param patronym отчество сотрудника
     * @param phone номер телефона сотрудника
     * @param city город сотрудника
     * @param street улица сотрудника
     * @param house номер дома сотрудника
     * @param appartment номер квартиры сотрудника
     * @see Employer#Employer(String, String, String, String, String, String, int, int)
     */
    public Employer(String surName, String name, String patronym, String phone, String city, String street, int house, int appartment) {
        super(surName, name, patronym, phone);
        this.city = city;
        this.street = street;
        this.house = house;
        this.appartment = appartment;
    }
    /**
     * Конструктор – создание нового экземпляра с заданными параметрами
     * @param employerID идентификатор сотрудника
     * @param surName фамилия сотрудника
     * @param name имя сотрудника
     * @param patronym отчество сотрудника
     * @param phone номер телефона сотрудника
     * @param city город сотрудника
     * @param street улица сотрудника
     * @param house номер дома сотрудника
     * @param appartment номер квартиры сотрудника
     * @see Employer#Employer(long, String, String, String, String, String, String, int, int)
     */
    public Employer(long employerID,String surName, String name, String patronym, String phone,  String city, String street, int house, int appartment) {
        super(surName, name, patronym, phone);
        this.employerID = employerID;
        this.city = city;
        this.street = street;
        this.house = house;
        this.appartment = appartment;
    }
    /**
     * Конструктор – создание нового экземпляра с заданными параметрами
     *  @see Employer#Employer()
     */
    public Employer() {
    }
    /**
     * Функция получения значение поля {@link Employer#employerID}
     * @return возвращает идентификатор сотрудника
     */
    public long getEmployerID() {
        return employerID;
    }
    /**
     * Функция изменения значения поля {@link Employer#employerID}
     * @param employerID идентификатор сотрудника
     */
    public void setEmployerID(long employerID) {
        this.employerID = employerID;
    }
    /**
     * Функция получения значение поля {@link Employer#city}
     * @return возвращает город сотрудника
     */
    public String getCity() {
        return city;
    }
    /**
     * Функция изменения значения поля {@link Employer#city}
     * @param city город сотрудника
     */
    public void setCity(String city) {
        this.city = city;
    }
    /**
     * Функция получения значение поля {@link Employer#street}
     * @return возвращает улицу сотрудника
     */
    public String getStreet() {
        return street;
    }
    /**
     * Функция изменения значения поля {@link Employer#street}
     * @param street улицу сотрудника
     */
    public void setStreet(String street) {
        this.street = street;
    }
    /**
     * Функция получения значение поля {@link Employer#house}
     * @return возвращает номер дома сотрудника
     */
    public int getHouse() {
        return house;
    }
    /**
     * Функция изменения значения поля {@link Employer#house}
     * @param house номер дома сотрудника
     */
    public void setHouse(int house) {
        this.house = house;
    }
    /**
     * Функция получения значение поля {@link Employer#appartment}
     * @return возвращает номер квартиры сотрудника
     */
    public Integer getAppartment() {
        return appartment;
    }
    /**
     * Функция изменения значения поля {@link Employer#appartment}
     * @param appartment номер квартиры сотрудника
     */
    public void setAppartment(int appartment) {
        this.appartment = appartment;
    }
    public void setNullAppartment(){
        this.appartment = null;
    }
}
