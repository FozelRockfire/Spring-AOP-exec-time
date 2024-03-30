CREATE TABLE IF NOT EXISTS t_execution_time
(
    t_execution_time_id  BIGINT PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    t_class_name VARCHAR(255),
    t_method_name  VARCHAR(255),
    t_execution_time BIGINT
);
