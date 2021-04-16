import request from '@/utils/request'


// 分页查询流程信息管理列表
export function listPageDriveFlow(query) {
  return request({
    url: '/basics/driveFlow/pageList',
    method: 'get',
    params: query
  })
}


// 分页查询流程信息管理列表
export function listDriveFlow(query) {
  return request({
    url: '/basics/driveFlow/findList',
    method: 'get',
    params: query
  })
}

// 查询流程信息管理详细
export function getDriveFlow(${pkColumn.propertyName}) {
  return request({
    url: '/basics/driveFlow/' + ${pkColumn.propertyName},
    method: 'get'
  })
}

// 新增流程信息管理
export function addDriveFlow(data) {
  return request({
    url: '/basics/driveFlow',
    method: 'post',
    data: data
  })
}

// 修改流程信息管理
export function updateDriveFlow(data) {
  return request({
    url: '/basics/driveFlow',
    method: 'put',
    data: data
  })
}

// 删除流程信息管理
export function delDriveFlow(${pkColumn.propertyName}) {
  return request({
    url: '/basics/driveFlow/' + ${pkColumn.propertyName},
    method: 'delete'
  })
}


// 状态启用/停用流程信息管理
export function changeStatus(data) {
  return request({
    url: '/basics/driveFlow/changeStatus',
    method: 'post',
    data: data
  })
}

// 导出流程信息管理
export function exportDriveFlow(query) {
  return request({
    url: '/basics/driveFlow/exportXls',
    method: 'post',
    params: query,
    responseType: 'blob'
  })
}