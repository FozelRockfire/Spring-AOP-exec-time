CREATE TABLE IF NOT EXISTS t_classes
(
    class_id    BIGINT PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    class_name  VARCHAR(255)
);
