package com.zero.vqueue.services.repositories.config;

import com.zero.vqueue.VQueueApplication;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.CustomConversions;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.core.convert.CouchbaseCustomConversions;
import org.springframework.data.couchbase.repository.config.EnableReactiveCouchbaseRepositories;

@Getter
@Setter
@Configuration
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "vqueue.couchbase")
@EnableReactiveCouchbaseRepositories(basePackageClasses = VQueueApplication.class)
public class CouchbaseConfig extends AbstractCouchbaseConfiguration {
    private static final String DEFAULT_ATTRIBUTE_TYPE_NAME = "docType";
    
    private String connectionString;
    private String bucketName;
    private String userName;
    private String password;
    
    @Override
    public String typeKey() {
        return DEFAULT_ATTRIBUTE_TYPE_NAME;
    }
    
    @Override
    public CustomConversions customConversions() {
        List<?> converters = Arrays.asList(
                ZonedDateTimeToStringConverter.INSTANCE,
                StringToZonedDateTimeConverter.INSTANCE);
        return new CouchbaseCustomConversions(converters);
    }
    
    @WritingConverter
    public enum ZonedDateTimeToStringConverter implements Converter<ZonedDateTime, String> {
        INSTANCE;
        
        @Override
        public String convert(ZonedDateTime source) {
            return DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(source);
        }
    }
    
    @ReadingConverter
    public enum StringToZonedDateTimeConverter implements Converter<String, ZonedDateTime> {
        INSTANCE;
        
        @Override
        public ZonedDateTime convert(String source) {
            try {
                return OffsetDateTime.parse(source).toZonedDateTime();
            } catch (DateTimeParseException e) {
                return LocalDateTime.parse(source).atOffset(ZoneOffset.UTC).toZonedDateTime();
            }
        }
    }
}