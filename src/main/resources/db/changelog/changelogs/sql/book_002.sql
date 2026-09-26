CREATE TABLE if not exists book (
  id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  title VARCHAR(1000) NOT NULL,
  author varchar(500),
  isbn varchar(255) not null unique ,
  version int default 1,
  description varchar(1000),
  category varchar(255),
  blob_id bigint not null unique,

  created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
  created_by VARCHAR(255),
  updated_by VARCHAR(255),
  deleted_at TIMESTAMP WITH TIME ZONE  ,
  constraint fk_book_blob_id foreign key(blob_id) references blob(id)
);