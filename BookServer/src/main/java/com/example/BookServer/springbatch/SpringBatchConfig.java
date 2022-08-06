//package com.example.BookServer.springbatch;
//
//import com.example.BookServer.model.BookData;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.launch.support.RunIdIncrementer;
//import org.springframework.batch.item.ItemProcessor;
//import org.springframework.batch.item.ItemReader;
//import org.springframework.batch.item.ItemWriter;
//import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
//import org.springframework.batch.item.database.JdbcBatchItemWriter;
//import org.springframework.batch.item.file.FlatFileItemReader;
//import org.springframework.batch.item.file.LineMapper;
//import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
//import org.springframework.batch.item.file.mapping.DefaultLineMapper;
//import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.core.io.FileSystemResource;
//
//import javax.sql.DataSource;
//
//@Configuration
//@EnableBatchProcessing
//public class SpringBatchConfig {
//
//    @Autowired
//    private DataSource dataSource;
//
//    @Autowired
//    private JobBuilderFactory jobBuilderFactory;
//
//    @Autowired
//    private StepBuilderFactory stepBuilderFactory;
//
//
//    /*************** creating job ***************/
//    @Bean
//    public Job job(
////            JobBuilderFactory jobBuilderFactory,
////                   StepBuilderFactory stepBuilderFactory,
//                   ItemReader<BookData> itemReader,
//                   ItemProcessor<BookData, BookData> itemProcessor,
//                   ItemWriter<BookData> itemWriter) {
//
//        Step step = stepBuilderFactory.get("ETL-file-load")
//                .<BookData, BookData>chunk(100)
//                .reader(itemReader)
//                .processor(itemProcessor)
//                .writer(itemWriter)
//                .build();
//
//
//        return jobBuilderFactory.get("ETL-Load")
//                .incrementer(new RunIdIncrementer())
//                .start(step)
//                .build();
//    }
//
//    /*************** creating reader ***************/
//    @Bean
//    public FlatFileItemReader<BookData> itemReader() {
//
//        FlatFileItemReader<BookData> flatFileItemReader = new FlatFileItemReader<>();
//        flatFileItemReader.setResource(new FileSystemResource("D:\\BRIDGLABZ\\3.CFP\\Advanced Backend\\SpringMicroservices\\BookServer\\src\\main\\resources\\Book.csv"));
//        flatFileItemReader.setResource(new ClassPathResource("Book.csv"));
////        flatFileItemReader.setName("CSV-Reader");
//        flatFileItemReader.setLineMapper(lineMapper());
//        //set one line if getting error
//        flatFileItemReader.setLinesToSkip(1);
//        return flatFileItemReader;
//    }
//
//    @Bean
//    public LineMapper<BookData> lineMapper() {
//
//        DefaultLineMapper<BookData> defaultLineMapper = new DefaultLineMapper<>();
//        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
//        //        lineTokenizer.setDelimiter(",");
////        lineTokenizer.setStrict(false);
//        lineTokenizer.setNames(new String[]{"bookId", "bookName", "bookAuthor", "bookPrice", "quantity"});
//        lineTokenizer.setIncludedFields(new int[]{0,1,2,3,4});
//
//        BeanWrapperFieldSetMapper<BookData> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
//        fieldSetMapper.setTargetType(BookData.class);
//        defaultLineMapper.setLineTokenizer(lineTokenizer);
//        defaultLineMapper.setFieldSetMapper(fieldSetMapper);
//
//        return defaultLineMapper;
//    }
//
//    /*************** creating processor ***************/
//    @Bean
//    public BookItemProcessor itemProcessor(){
//        return new BookItemProcessor();
//    }
//
//    /*************** creating writer ***************/
//    @Bean
//    public JdbcBatchItemWriter<BookData> writer(){
//
//        JdbcBatchItemWriter<BookData> writer = new JdbcBatchItemWriter<>();
//        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<BookData>());
//        writer.setSql("insert into book(bookId,bookName,bookAuthor,bookPrice, quantity) values (:bookId,:bookName," +
//                ":bookAuthor,:bookPrice,:quantity)");
//        writer.setDataSource((this.dataSource));
//        return writer;
//    }
//}
