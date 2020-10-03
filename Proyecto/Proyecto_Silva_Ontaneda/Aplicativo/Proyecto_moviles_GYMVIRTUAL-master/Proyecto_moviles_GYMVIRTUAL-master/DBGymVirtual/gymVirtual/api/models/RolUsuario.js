/**
 * RolUsuario.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {
    id_rolUsuario:{
      type: 'number',
      autoIncrement:true,
      columnName:'idRolUsuario'
    },

    rol:{
      model:'rol'
    },

    usuario:{
      model: 'usuario'
    }
  },

};

