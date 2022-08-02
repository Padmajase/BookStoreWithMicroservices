package com.example.CartServer.springbatch;

import com.example.CartServer.model.CartData;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;


    /*************** creating job ***************/
    @Bean
    public Job job(
//            JobBuilderFactory jobBuilderFactory,
//                   StepBuilderFactory stepBuilderFactory,
                   ItemReader<CartData> itemReader,
                   ItemProcessor<CartData, CartData> itemProcessor,
                   ItemWriter<CartData> itemWriter) {

        Step step = stepBuilderFactory.get("ETL-file-load")
                .<CartData, CartData>chunk(100)
                .reader(itemReader)
                .processor(itemProcessor)
                .writer(itemWriter)
                .build();


        return jobBuilderFactory.get("ETL-Load")
                .incrementer(new RunIdIncrementer())
                .start(step)
                .build();
    }

    /*************** creating reader ***************/
    @Bean
    public FlatFileItemReader<CartData> itemReader() {

        FlatFileItemReader<CartData> flatFileItemReader = new FlatFileItemReader<>();
        flatFileItemReader.setResource(new FileSystemResource("D:\\BRIDGLABZ\\3.CFP\\Advanced Backend\\SpringMicroservices\\CartServer\\src\\main\\resources\\Cart.csv"));
        flatFileItemReader.setResource(new ClassPathResource("Cart.csv"));
//        flatFileItemReader.setName("CSV-Reader");
        flatFileItemReader.setLineMapper(lineMapper());
        //set one line if getting error
        flatFileItemReader.setLinesToSkip(1);
        return flatFileItemReader;
    }

    @Bean
    public LineMapper<CartData> lineMapper() {

        DefaultLineMapper<CartData> defaultLineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        //        lineTokenizer.setDelimiter(",");
//        lineTokenizer.setStrict(false);
        lineTokenizer.setNames(new String[]{"cartId", "bookData", "userData", "quantity"});
        lineTokenizer.setIncludedFields(new int[]{0,1,2,3});

        BeanWrapperFieldSetMapper<CartData> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(CartData.class);
        defaultLineMapper.setLineTokenizer(lineTokenizer);
        defaultLineMapper.setFieldSetMapper(fieldSetMapper);

        return defaultLineMapper;
    }

    /*************** creating processor ***************/
    @Bean
    public CartItemProcessor itemProcessor(){
        return new CartItemProcessor();
    }

    /*************** creating writer ***************/
    @Bean
    public JdbcBatchItemWriter<CartData> writer(){

        JdbcBatchItemWriter<CartData> writer = new JdbcBatchItemWriter<>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<CartData>());
        writer.setSql("insert into cart_data(cartId,booData,userData,quantity) values (:cartId,:booData,:userData,:quantity)");
        writer.setDataSource((this.dataSource));
        return writer;
    }
}
