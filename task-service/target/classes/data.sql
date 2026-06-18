SET FOREIGN_KEY_CHECKS = 0;

TRUNCATE TABLE historico_tarefa;
TRUNCATE TABLE bloqueio_tarefa;
TRUNCATE TABLE tarefas;

INSERT INTO tarefas (nome, descricao, ids_responsaveis, status, id_projeto, data_criacao, data_inicio_bloqueio, data_fim_bloqueio)
VALUES
  ('Criar wireframes', 'Criação dos wireframes do site institucional', '1', 'TO_DO', 1, NOW(), NULL, NULL),
  ('Desenvolver frontend', 'Implementação das telas em React', '1,5', 'DOING', 1, NOW(), NULL, NULL),
  ('Revisar layout', 'Revisão do layout com o cliente', '5', 'TO_DO', 1, NOW(), NULL, NULL),
  ('Configurar CI/CD', 'Pipeline de deploy do app mobile', '1', 'TO_DO', 2, NOW(), NULL, NULL),
  ('Desenvolver tela de login', 'Tela de autenticação do app mobile', '1,5', 'DOING', 2, NOW(), NULL, NULL),
  ('Testes unitários mobile', 'Cobertura de testes do app Android e iOS', '5', 'BLOCKED', 2, NOW(), NOW(), '2026-06-15 18:00:00'),
  ('Modelagem de dados', 'Modelagem do banco de dados do ERP', '1', 'DONE', 3, NOW(), NULL, NULL),
  ('Testes de integração', 'Testes do sistema ERP', '1,5', 'DONE', 3, NOW(), NULL, NULL),
  ('Documentação técnica', 'Documentação das APIs do ERP', '5', 'DONE', 3, NOW(), NULL, NULL),
  ('Treinamento de usuários', 'Capacitação da equipe para uso do ERP', '1', 'DONE', 3, NOW(), NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;