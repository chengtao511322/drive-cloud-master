import request from '@/utils/request'

                      
// 分页查询系统配置参数表列表
export function listPageSysParam(query) {
  return request({
    url: '/admin/sysParam/pageList',
    method: 'post',
    data: query
  })
}


// 分页查询系统配置参数表列表
export function listSysParam(query) {
  return request({
    url: '/admin/sysParam/findList',
    method: 'post',
    data: query
  })
}

// 查询系统配置参数表详细
export function getSysParam(prmEnumId) {
  return request({
    url: '/admin/sysParam/' + prmEnumId,
    method: 'get'
  })
}

// 新增系统配置参数表
export function addSysParam(data) {
  return request({
    url: '/admin/sysParam',
    method: 'post',
    data: data
  })
}

// 修改系统配置参数表
export function updateSysParam(data) {
  return request({
    url: '/admin/sysParam',
    method: 'put',
    data: data
  })
}

// 删除系统配置参数表
export function delSysParam(prmEnumId) {
  return request({
    url: '/admin/sysParam/' + prmEnumId,
    method: 'delete'
  })
}


// 状态启用/停用系统配置参数表
export function changeStatus(data) {
  return request({
    url: '/admin/sysParam/changeStatus',
    method: 'post',
    data: data
  })
}

// 导出系统配置参数表
export function exportSysParam(query) {
  return request({
    url: '/admin/sysParam/exportXls',
    method: 'post',
    params: query,
    responseType: 'blob'
  })
}