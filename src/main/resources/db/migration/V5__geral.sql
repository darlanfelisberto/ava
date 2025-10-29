ALTER TABLE public.imagen ADD folder varchar(10) NOT NULL;
ALTER TABLE public.produto ADD cod_sku varchar NULL;
ALTER TABLE public.produto ADD modelo varchar NULL;
ALTER TABLE public.marca RENAME COLUMN descricao TO nome;
