CREATE TABLE Users(user_id INTEGER GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY, name VARCHAR(40), password VARCHAR(40), phoneNumber VARCHAR(20));