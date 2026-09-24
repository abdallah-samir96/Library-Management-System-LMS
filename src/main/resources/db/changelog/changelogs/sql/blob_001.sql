CREATE TABLE blob(
    id bigint generated always as identity primary key,
    file_name varchar(500) not null,
    directory varchar(255) not null,
    extension varchar(255) not null,
    content_type VARCHAR(255) NOT NULL,
    size_in_bytes bigint not null default 0,
    path varchar(1000) not null unique,

    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(255),
    updated_by VARCHAR(255),
    deleted_at TIMESTAMP WITH TIME ZONE
);