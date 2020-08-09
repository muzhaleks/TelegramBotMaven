package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "candles")
public class Candle {

    @Id
    @GeneratedValue(generator = "increment")
    private Long id;

    @Column(name = "candle_name", nullable = false, length = 150)
    private String candleName;

    @Column(name = "candle_description", length = 700)
    private String candleDescription;


    public Candle() {
    }

    public Candle(String candleName) {
        this.candleName = candleName;
    }

    public Candle(String candleName, String candleDescription) {
        this.candleName = candleName;
        this.candleDescription = candleDescription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCandleName() {
        return candleName;
    }

    public void setCandleName(String candleName) {
        this.candleName = candleName;
    }

    public String getCandleDescription() {
        return candleDescription;
    }

    public void setCandleDescription(String candleDescription) {
        this.candleDescription = candleDescription;
    }
}
