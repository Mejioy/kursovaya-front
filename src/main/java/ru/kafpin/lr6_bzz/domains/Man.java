package ru.kafpin.lr6_bzz.domains;
/**
 * Класс Человек со свойствами <b>surName</b>, <b>name</b>, <b>patronym</b> и <b>phone</b>.
 * <p>
 *     Данный класс разработан для наследования другими классами.
 * </p>
 * @author Ярослав Кокурин
 * @version 1.0
 */
public abstract class Man {
    /** Поле имя*/
    private String name;
    /** Поле фамилия*/
    private String surName;
    /** Поле отчество*/
    private String patronym;
    /** Поле номер телефона*/
    private String phone;
    /**
     * Конструктор – создание нового экземпляра с заданными параметрами
     * @param surName фамилия
     * @param name имя
     * @param patronym отчество
     * @param phone номер телефона
     * @see Man#Man(String, String, String, String)
     */
    public Man(String surName, String name, String patronym, String phone) {
        this.surName = surName;
        this.name = name;
        this.patronym = patronym;
        this.phone = phone;
    }
    /**
     * Конструктор – создание нового экземпляра с заданными параметрами
     * @see Man#Man()
     */
    public Man() {
    }
    /**
     * Функция получения значение поля {@link Man#name}
     * @return возвращает имя
     */
    public String getName() {
        return name;
    }
    /**
     * Функция изменения значения поля {@link Man#name}
     * @param name имя
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Функция получения значение поля {@link Man#surName}
     * @return возвращает фамилию
     */
    public String getSurName() {
        return surName;
    }
    /**
     * Функция изменения значения поля {@link Man#surName}
     * @param surName фамилия
     */
    public void setSurName(String surName) {
        this.surName = surName;
    }
    /**
     * Функция получения значение поля {@link Man#patronym}
     * @return возвращает отчество
     */
    public String getPatronym() {
        return patronym;
    }
    /**
     * Функция изменения значения поля {@link Man#patronym}
     * @param patronym отчество
     */
    public void setPatronym(String patronym) {
        this.patronym = patronym;
    }
    /**
     * Функция получения значение поля {@link Man#phone}
     * @return возвращает номер телефона
     */
    public String getPhone() {
        return phone;
    }
    /**
     * Функция изменения значения поля {@link Man#phone}
     * @param phone номер телефона
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
}
