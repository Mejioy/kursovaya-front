package ru.kafpin.lr6_bzz.domains;
import java.util.Date;
/**
 * Класс Оказанная услуга со свойствами <b>providedServiceID</b>, <b>idservice</b>, <b>idemployer</b>, <b>idautomobile</b> и <b>datetime</b>.
 * <p>
 *     Данный класс позволяет описать экземпляр оказанной услуги с заданными идентификатором услуги,
 *     идентификатором сотрудника, идентификатором автомобиля и датой оказания услуги.
 * </p>
 * @author Ярослав Кокурин
 * @version 1.0
 */
public class ProvidedService {
    /** Поле идентификатор*/
    private long providedServiceID;
    /** Поле идентификатор услуги*/
    private long idservice;
    /** Поле идентификатор сотрудника*/
    private long idemployer;
    /** Поле идентификатор автомобиля*/
    private long idautomobile;
    /** Поле дата оказания услуги*/
    private Date datetime;
    /**
     * Конструктор – создание нового экземпляра с заданными параметрами
     * @param datetime дата оказания услуги
     * @see ProvidedService#ProvidedService(Date)
     */
    public ProvidedService(Date datetime) {
        this.datetime = datetime;
    }
    /**
     * Функция получения информации об оказанной услуге
     * @return возвращает строку с информацией об оказанной услуге
     */
    @Override
    public String toString() {
        return "ProvidedService{" +
                "providedServiceID=" + providedServiceID +
                ", idservice=" + idservice +
                ", idemployer=" + idemployer +
                ", idautomobile=" + idautomobile +
                ", datetime=" + datetime +
                '}';
    }
    /**
     * Конструктор – создание нового экземпляра с заданными параметрами
     * @param idservice идентификатор услуги
     * @param idemployer идентификатор сотрудника
     * @param idautomobile идентификатор автомобиля
     * @param datetime дата оказания услуги
     * @see ProvidedService#ProvidedService(long, long, long, Date)
     */
    public ProvidedService(long idservice, long idemployer, long idautomobile, Date datetime) {
        this.idservice = idservice;
        this.idemployer = idemployer;
        this.idautomobile = idautomobile;
        this.datetime = datetime;
    }
    /**
     * Конструктор – создание нового экземпляра с заданными параметрами
     * @param providedServiceID идентификатор оказанной услуги
     * @param idservice идентификатор услуги
     * @param idemployer идентификатор сотрудника
     * @param idautomobile идентификатор автомобиля
     * @param datetime дата оказания услуги
     * @see ProvidedService#ProvidedService(long, long, long, long, Date)
     */
    public ProvidedService(long providedServiceID, long idservice, long idemployer, long idautomobile, Date datetime) {
        this.providedServiceID = providedServiceID;
        this.idservice = idservice;
        this.idemployer = idemployer;
        this.idautomobile = idautomobile;
        this.datetime = datetime;
    }
    /**
     * Функция преобразования поля {@link ProvidedService#datetime}
     * @return возвращает экземпляр класса java.sql.Date с значением поля datetime
     */
    public java.sql.Date getSqlDateTime(){
        return new java.sql.Date(datetime.getTime()) ;
    }
    /**
     * Функция получения значение поля {@link ProvidedService#datetime}
     * @return возвращает дату оказанной услуги
     */
    public Date getDatetime() {
        return datetime;
    }
    /**
     * Функция изменения значения поля {@link ProvidedService#datetime}
     * @param datetime дата оказанной услуги
     */
    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }
    /**
     * Функция получения значение поля {@link ProvidedService#idautomobile}
     * @return возвращает идентификатор автомобиля
     */
    public long getIdautomobile() {
        return idautomobile;
    }
    /**
     * Функция изменения значения поля {@link ProvidedService#idautomobile}
     * @param idautomobile идентификатор автомобиля
     */
    public void setIdautomobile(long idautomobile) {
        this.idautomobile = idautomobile;
    }
    /**
     * Функция получения значение поля {@link ProvidedService#idservice}
     * @return возвращает идентификатор услуги
     */
    public long getIdservice() {
        return idservice;
    }
    /**
     * Функция изменения значения поля {@link ProvidedService#idservice}
     * @param idservice идентификатор услуги
     */
    public void setIdservice(long idservice) {
        this.idservice = idservice;
    }
    /**
     * Функция получения значение поля {@link ProvidedService#idemployer}
     * @return возвращает идентификатор сотрудника
     */
    public long getIdemployer() {
        return idemployer;
    }
    /**
     * Функция изменения значения поля {@link ProvidedService#idemployer}
     * @param idemployer идентификатор сотрудника
     */
    public void setIdemployer(long idemployer) {
        this.idemployer = idemployer;
    }
    /**
     * Функция получения значение поля {@link ProvidedService#providedServiceID}
     * @return возвращает идентификатор оказанной услуги
     */
    public long getProvidedServiceID() {
        return providedServiceID;
    }
    /**
     * Функция изменения значения поля {@link ProvidedService#providedServiceID}
     * @param providedServiceID идентификатор оказанной услуги
     */
    public void setProvidedServiceID(long providedServiceID) {
        this.providedServiceID = providedServiceID;
    }
}
