package silab.nst.dan9.dataAccess.domain;

import java.util.Objects;

public class Stats {

    String key;
    String value;

    public Stats() {
    }

    public Stats(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stats stats = (Stats) o;
        return key.equals(stats.key) && value.equals(stats.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }

    @Override
    public String toString() {
        return "Stats{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
