package ru.kafpin.lr6_bzz.domains;
/**
 * Класс Клиент со свойствами <b>clientID</b>, <b>surName</b>, <b>name</b>, <b>patronym</b> и <b>phone</b>.
 * <p>
 *     Данный класс позволяет описать экземпляр клиента с заданными фамилией,
 *     именем, отчеством и номером телефона.
 * </p>
 * @author Ярослав Кокурин
 * @version 1.0
 */
public class Client extends Man {
    /** Поле идентификатор*/
    private long clientID;
    /**
     * Конструктор – создание нового экземпляра с заданными параметрами
     * @see Client#Client()
     */
    public Client() {
    }
    /**
     * Функция получения информации о клиенте
     * @return возвращает строку с информацией о клиенте
     */
    @Override
    public String toString() {
        return getSurName()+' '+getName()+' '+getPatronym();
    }
    /**
     * Конструктор – создание нового экземпляра с заданными параметрами
     * @param clientID идентификатор клиента
     * @param surName фамилия клиента
     * @param name имя клиента
     * @param patronym отчество клиента
     * @param phone номер телефона клиента
     * @see Client#Client(long, String, String, String, String)
     */
    public Client(long clientID, String surName, String name, String patronym, String phone) {
        super(surName, name, patronym, phone);
        this.clientID = clientID;
    }
    /**
     * Конструктор – создание нового экземпляра с заданными параметрами
     * @param surName фамилия клиента
     * @param name имя клиента
     * @param patronym отчество клиента
     * @param phone номер телефона клиента
     * @see Client#Client(String, String, String, String)
     */
    public Client(String surName, String name, String patronym, String phone) {
        super(surName, name, patronym, phone);
    }
    /**
     * Функция получения значение поля {@link Client#clientID}
     * @return возвращает идентификатор клиента
     */
    public long getClientID() {
        return clientID;
    }
    /**
     * Функция изменения значения поля {@link Client#clientID}
     * @param clientID идентификатор клиента
     */
    public void setClientID(long clientID) {
        this.clientID = clientID;
    }
}
