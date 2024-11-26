package ru.kafpin.lr6_bzz.domains;
/**
 * Класс Услуга со свойствами <b>serviceID</b>, <b>name</b>, <b>name</b>, <b>duration</b>, <b>price</b> и <b>description</b>.
 * <p>
 *     Данный класс позволяет описать экземпляр услуги с заданными названием,
 *     продолжительностью, стоимостью и описанием.
 * </p>
 * @author Ярослав Кокурин
 * @version 1.0
 */
public class Service {
    /** Поле идентификатор*/
    private long serviceID;
    /** Поле название*/
    private String name;
    /** Поле продолжительность*/
    private int duration;
    /** Поле стоимость*/
    private int price;
    /** Поле описание*/
    private String description;
    /**
     * Функция получения информации об услуге
     * @return возвращает строку с информацией об услуге
     */
    @Override
    public String toString() {
        return getName();
    }
    /**
     * Конструктор – создание нового экземпляра с заданными параметрами
     * @param name название услуги
     * @param duration продолжительность услуги
     * @param price стоимость услуги
     * @see Service#Service(String, int, int)
     */
    public Service(String name, int duration, int price) {
        this.name = name;
        this.duration = duration;
        this.price = price;
    }
    /**
     * Конструктор – создание нового экземпляра с заданными параметрами
     * @param serviceID идентификатор услуги
     * @param name название услуги
     * @param duration продолжительность услуги
     * @param price стоимость услуги
     * @see Service#Service(long, String, int, int)
     */
    public Service(long serviceID, String name, int duration, int price) {
        this.serviceID = serviceID;
        this.name = name;
        this.duration = duration;
        this.price = price;
    }
    /**
     * Конструктор – создание нового экземпляра с заданными параметрами
     * @param name название услуги
     * @param duration продолжительность услуги
     * @param price стоимость услуги
     * @param description описание услуги
     * @see Service#Service(String, int, int, String)
     */
    public Service(String name, int duration, int price, String description) {
        this.name = name;
        this.duration = duration;
        this.price = price;
        this.description = description;
    }
    /**
     * Конструктор – создание нового экземпляра с заданными параметрами
     * @param serviceID идентификатор услуги
     * @param name название услуги
     * @param duration продолжительность услуги
     * @param price стоимость услуги
     * @param description описание услуги
     * @see Service#Service(long, String, int, int, String)
     */
    public Service(long serviceID, String name, int duration, int price, String description) {
        this.serviceID = serviceID;
        this.name = name;
        this.duration = duration;
        this.price = price;
        this.description = description;
    }
    /**
     * Конструктор – создание нового экземпляра с заданными параметрами
     * @see Service#Service()
     */
    public Service() {
    }
    /**
     * Функция получения значение поля {@link Service#serviceID}
     * @return возвращает идентификатор услуги
     */
    public long getServiceID() {
        return serviceID;
    }
    /**
     * Функция изменения значения поля {@link Service#serviceID}
     * @param serviceID идентификатор услуги
     */
    public void setServiceID(long serviceID) {
        this.serviceID = serviceID;
    }
    /**
     * Функция получения значение поля {@link Service#name}
     * @return возвращает название услуги
     */
    public String getName() {
        return name;
    }
    /**
     * Функция изменения значения поля {@link Service#name}
     * @param name название услуги
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Функция получения значение поля {@link Service#duration}
     * @return возвращает продолжительность услуги
     */
    public int getDuration() {
        return duration;
    }
    /**
     * Функция изменения значения поля {@link Service#duration}
     * @param duration продолжительность услуги
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }
    /**
     * Функция получения значение поля {@link Service#price}
     * @return возвращает стоимость услуги
     */
    public int getPrice() {
        return price;
    }
    /**
     * Функция изменения значения поля {@link Service#price}
     * @param price стоимость услуги
     */
    public void setPrice(int price) {
        this.price = price;
    }
    /**
     * Функция получения значение поля {@link Service#description}
     * @return возвращает описание услуги
     */
    public String getDescription() {
        return description;
    }
    /**
     * Функция изменения значения поля {@link Service#description}
     * @param description описание услуги
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
