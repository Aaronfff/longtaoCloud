package fun.longtao.customer.support;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class MoneyJsonComponent {

    public static class MoneyDeserializer extends JsonDeserializer<Money> {

        @Override
        public Money deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            return Money.of(CurrencyUnit.of("CNY"), p.getDecimalValue());
        }
    }

    public static class MoneySerializer extends JsonSerializer<Money>{
        @Override
        public void serialize(Money value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            gen.writeNumber(value.getAmount());
        }
    }

}
