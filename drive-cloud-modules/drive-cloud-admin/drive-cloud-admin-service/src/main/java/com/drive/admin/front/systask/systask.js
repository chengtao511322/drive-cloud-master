import request from '@/utils/request'

                        
// 分页查询系统任务表列表
export function listPageSysTask(query) {
  return request({
    url: '/admin/sysTask/pageList',
    method: 'post',
    data: query
  })
}


// 分页查询系统任务表列表
export function listSysTask(query) {
  return request({
    url: '/admin/sysTask/findList',
    method: 'post',
    data: query
  })
}

// 查询系统任务表详细
export function getSysTask(stTaskId) {
  return request({
    url: '/admin/sysTask/' + stTaskId,
    method: 'get'
  })
}

// 新增系统任务表
export function addSysTask(data) {
  return request({
    url: '/admin/sysTask',
    method: 'post',
    data: data
  })
}

// 修改系统任务表
export function updateSysTask(data) {
  return request({
    url: '/admin/sysTask',
    method: 'put',
    data: data
  })
}

// 删除系统任务表
export function delSysTask(stTaskId) {
  return request({
    url: '/admin/sysTask/' + stTaskId,
    method: 'delete'
  })
}


// 状态启用/停用系统任务表
export function changeStatus(data) {
  return request({
    url: '/admin/sysTask/changeStatus',
    method: 'post',
    data: data
  })
}

// 导出系统任务表
export function exportSysTask(query) {
  return request({
    url: '/admin/sysTask/exportXls',
    method: 'post',
    params: query,
    responseType: 'blob'
  })
}