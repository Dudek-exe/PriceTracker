CREATE TABLE public.product
(
    id           bigint                 NOT NULL,
    border_price numeric(19, 2),
    price_time   timestamp with time zone NOT NULL,
    name         character varying(200) NOT NULL,
    price        numeric(19, 2)         NOT NULL
);
