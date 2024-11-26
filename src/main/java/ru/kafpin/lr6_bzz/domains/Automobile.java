package ru.kafpin.lr6_bzz.domains;
/**
 * Класс Автомобиль со свойствами <b>automobileID</b>,<b>mark</b>,<b>model</b>,<b>gosnumber</b> и <b>idclient</b>.
 * <p>
 *     Данный класс позволяет описать экземпляр автомобиля с заданными маркой,
 *     моделью, гос.номером и владельцем.
 * </p>
 * @author Ярослав Кокурин
 * @version 1.0
 */
public class Automobile {
    /** Поле идентификатор*/
    private long automobileID;
    /** Поле марка*/
    private String mark;
    /** Поле модель*/
    private String model;
    /** Поле гос.номер*/
    private String gosnumber;
    /** Поле идентификатор владельца*/
    private long idclient;
    /**
     * Конструктор – создание нового экземпляра с заданными параметрами
     * @param idclient идентификатор владельца автомобиля
     * @see Automobile#Automobile(long)
     */
    public Automobile(long idclient) {
        this.idclient = idclient;
    }
    /**
     * Функция получения информации об автомобиле
     * @return возвращает строку с информацией об автомобиле
     */
    @Override
    public String toString() {
        return getMark()+' '+getModel()+' '+getGosnumber();
    }
    /**
     * Конструктор – создание нового экземпляра с заданными параметрами
     * @param mark марка автомобиля
     * @param model модель автомобиля
     * @param gosnumber гос.номер автомобиля
     * @param idclient идентификатор владельца автомобиля
     * @see Automobile#Automobile(String, String, String, long)
     */
    public Automobile(String mark, String model, String gosnumber, long idclient) {
        this.mark = mark;
        this.model = model;
        this.gosnumber = gosnumber;
        this.idclient = idclient;
    }
    /**
     * Конструктор – создание нового экземпляра с заданными параметрами
     * @param automobileID идентификатор автомобиля
     * @param mark марка автомобиля
     * @param model модель автомобиля
     * @param gosnumber гос.номер автомобиля
     * @param idclient идентификатор владельца автомобиля
     * @see Automobile#Automobile(long,String, String, String, long)
     */
    public Automobile(long automobileID, String mark, String model, String gosnumber, long idclient) {
        this.automobileID = automobileID;
        this.mark = mark;
        this.model = model;
        this.gosnumber = gosnumber;
        this.idclient = idclient;
    }
    /**
     * Функция получения значение поля {@link Automobile#automobileID}
     * @return возвращает идентификатор автомобиля
     */
    public long getAutomobileID() {
        return automobileID;
    }
    /**
     * Функция изменения значения поля {@link Automobile#automobileID}
     * @param automobileID идентификатор автомобиля
     */
    public void setAutomobileID(long automobileID) {
        this.automobileID = automobileID;
    }
    /**
     * Функция получения значение поля {@link Automobile#idclient}
     * @return возвращает идентификатор владельца автомобиля
     */
    public long getIdclient() {
        return idclient;
    }
    /**
     * Функция изменения значения поля {@link Automobile#idclient}
     * @param idclient идентификатор владельца автомобиля
     */
    public void setIdclient(long idclient) {
        this.idclient = idclient;
    }
    /**
     * Функция получения значение поля {@link Automobile#mark}
     * @return возвращает марку автомобиля
     */
    public String getMark() {
        return mark;
    }
    /**
     * Функция изменения значения поля {@link Automobile#mark}
     * @param mark марка автомобиля
     */
    public void setMark(String mark) {
        this.mark = mark;
    }
    /**
     * Функция получения значение поля {@link Automobile#model}
     * @return возвращает модель автомобиля
     */
    public String getModel() {
        return model;
    }
    /**
     * Функция изменения значения поля {@link Automobile#model}
     * @param model модель автомобиля
     */
    public void setModel(String model) {
        this.model = model;
    }
    /**
     * Функция получения значение поля {@link Automobile#gosnumber}
     * @return возвращает гос.номер автомобиля
     */
    public String getGosnumber() {
        return gosnumber;
    }
    /**
     * Функция изменения значения поля {@link Automobile#gosnumber}
     * @param gosnumber гос.номер автомобиля
     */
    public void setGosnumber(String gosnumber) {
        this.gosnumber = gosnumber;
    }
}
