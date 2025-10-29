INSERT INTO public.status (id_status, descricao)VALUES
(0, 'Ativo'),
(1, 'Inativo');

INSERT INTO public.tipo_unidade (id_status, id_unidade, descricao, nome, sigla) VALUES
(0, 0, 'Caixa', 'Caixa', 'cx'),
(0, 1, 'Unidade', 'Unidade', 'un'),
(0, 2, 'Duzia', 'Duzia', 'dz');


INSERT INTO public.marca (id_marca, descricao) VALUES
    (1, 'Intel'),
    (2, 'AMD');